package com.skipthedishes.api.repositories.dish;

import com.skipthedishes.api.entities.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class DishRepositoryImpl implements DishRepositoryQuery {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Dish> find(String idRestaurant, String text) {
        return mongoTemplate.find(Query.query(new Criteria()
                .and("idRestaurant").is(idRestaurant)
                .orOperator(
                        Criteria.where("name").regex(text, "i"),
                        Criteria.where("description").regex(text, "i")
                )
        ), Dish.class);
    }
}
