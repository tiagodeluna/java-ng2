package com.skipthedishes.api.services.event.listener;

import com.skipthedishes.api.entities.Dish;
import com.skipthedishes.api.entities.Item;
import com.skipthedishes.api.entities.Order;
import com.skipthedishes.api.entities.Restaurant;
import com.skipthedishes.api.repositories.DishRepository;
import com.skipthedishes.api.repositories.RestaurantRepository;
import com.skipthedishes.api.services.event.OrderFinishedEvent;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Component
public class OrderFinishedListener implements ApplicationListener<OrderFinishedEvent> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private DishRepository dishRepository;


    @Override
    public void onApplicationEvent(OrderFinishedEvent orderFinishedEvent) {
        final Order order = orderFinishedEvent.getOrder();

        calculateRestaurantRating(order);

        calculateDishRating(order);
    }

    private void calculateRestaurantRating(Order order) {
        BigDecimal a = BigDecimal.valueOf(getCountRestaurantRating(order));
        BigDecimal b = BigDecimal.valueOf(getSumRestaurantRating(order).getTotal());

        BigDecimal result = b.divide(a.multiply(BigDecimal.valueOf(5))).multiply(BigDecimal.TEN);
        Restaurant restaurant = restaurantRepository.findOne(order.getRestaurantId());
        restaurant.setRating(result.doubleValue());

        restaurantRepository.save(restaurant);
    }

    private Long getCountRestaurantRating(Order order) {
        Criteria criteria = new Criteria();
        criteria.and("restaurantId").is(order.getRestaurantId());
        return mongoTemplate.count(Query.query(criteria), Order.class);
    }

    private RestaurantCount getSumRestaurantRating(Order order) {

        Aggregation agg = newAggregation(
                match(Criteria.where("restaurantId").is(order.getRestaurantId())),
                group("restaurantId").sum("reviewRating").as("total")
        );

        return mongoTemplate.aggregate(agg, Order.class, RestaurantCount.class).getUniqueMappedResult();
    }

    private void calculateDishRating(Order order) {
        order.getItems().forEach(item -> {
            BigDecimal a = BigDecimal.valueOf(getCountDishRating(item).size());
            BigDecimal b = BigDecimal.valueOf(getSumDishRating(item).stream().map(a1 -> a1.getReviewRating()).reduce((a1, b1) -> a1 + b1).orElse(0));

            BigDecimal result = b.divide(a.multiply(BigDecimal.valueOf(5))).multiply(BigDecimal.TEN);

            Dish dish = dishRepository.findOne(item.getDish().getId());
            dish.setRating(result.doubleValue());

            dishRepository.save(dish);
        });
    }

    private List<Order> getCountDishRating(Item item) {
        Criteria criteria = new Criteria();
        criteria.andOperator(
                Criteria.where("items.dish._id").is(new ObjectId(item.getDish().getId()))
        );

        Query query = Query.query(criteria);
        query.fields().include("items.reviewRating");
        return mongoTemplate.find(query, Order.class);

    }

    private List<DishCount> getSumDishRating(Item item) {
        Aggregation agg = newAggregation(
                match(Criteria.where("items.dish._id").is(new ObjectId(item.getDish().getId()))),
                project("items.reviewRating")
        );

        return mongoTemplate.aggregate(agg, Order.class, DishCount.class).getMappedResults();
    }
}

@Getter
@Setter
class RestaurantCount {
    private String restaurantId;
    private long total;
}

@Getter
@Setter
class DishCount {
    private Integer reviewRating;
}