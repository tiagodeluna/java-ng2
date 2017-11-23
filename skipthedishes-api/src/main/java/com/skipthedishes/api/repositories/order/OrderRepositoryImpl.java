package com.skipthedishes.api.repositories.order;

import com.skipthedishes.api.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepositoryQuery {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Order> findOrderNotReviewed(String customerId) {
        return mongoTemplate.find(Query.query(new Criteria()
                .andOperator(
                        Criteria.where("customer.id").is(customerId),
                        Criteria.where("reviewRating").not().exists(true)
                )
        ), Order.class);
    }
}
