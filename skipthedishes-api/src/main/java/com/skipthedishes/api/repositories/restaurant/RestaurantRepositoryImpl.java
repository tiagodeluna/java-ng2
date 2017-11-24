package com.skipthedishes.api.repositories.restaurant;

import com.skipthedishes.api.entities.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class RestaurantRepositoryImpl implements RestaurantRepositoryQuery {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Restaurant> find(String text) {
        return mongoTemplate.find(Query.query(new Criteria()
                .orOperator(
                        Criteria.where("name").regex(text, "i"),
                        Criteria.where("address").regex(text, "i"),
                        Criteria.where("timeToWait").regex(text, "i"),
                        Criteria.where("deliveryFee").regex(text, "i")
                )
        ), Restaurant.class);
    }
}
