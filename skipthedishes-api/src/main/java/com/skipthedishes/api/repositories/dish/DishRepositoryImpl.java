package com.skipthedishes.api.repositories.dish;

import com.skipthedishes.api.entities.CategoriesEnum;
import com.skipthedishes.api.entities.Customer;
import com.skipthedishes.api.entities.Dish;
import com.skipthedishes.api.entities.TagsEnum;
import com.skipthedishes.api.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collections;
import java.util.List;

public class DishRepositoryImpl implements DishRepositoryQuery {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Dish> find(String text, int offset, int size) {
        Criteria criteriaDefinition = new Criteria()
                .orOperator(
                        Criteria.where("name").regex(text, "i"),
                        Criteria.where("description").regex(text, "i")
                );
        Query query = Query.query(criteriaDefinition);
        query.skip(offset).limit(size);
        query.with(new Sort(Sort.Direction.DESC,"rating"));
        return mongoTemplate.find(query, Dish.class);
    }

    @Override
    public List<Dish> findByCategoryAndTag(String restaurantId, CategoriesEnum category, TagsEnum tag) {
        Dish example = new Dish();
        example.setRestaurantId(restaurantId);
        if (category != null) {
            example.setCategories(Collections.singleton(category));
        }
        if (tag != null) {
            example.setTags(Collections.singleton(tag));
        }

        Query query = Query.query(Criteria.byExample(example));

        return mongoTemplate.find(query, Dish.class);
    }

    @Override
    public List<Dish> findFavoriteByCustomerId(String customerId) {
        Customer customer = customerRepository.findOne(customerId);

        Criteria criteria = new Criteria();
        criteria.and("id").in(customer.getFavoriteDishes());

        return mongoTemplate.find(Query.query(criteria), Dish.class);
    }
}
