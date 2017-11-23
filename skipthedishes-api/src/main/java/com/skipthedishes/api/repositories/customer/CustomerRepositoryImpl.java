package com.skipthedishes.api.repositories.customer;

import com.skipthedishes.api.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepositoryQuery {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Customer> find(String text) {
        return mongoTemplate.find(Query.query(new Criteria()
                .orOperator(
                        Criteria.where("firstName").regex(text, "i"),
                        Criteria.where("lastName").regex(text, "i")
                )
        ), Customer.class);
    }
}
