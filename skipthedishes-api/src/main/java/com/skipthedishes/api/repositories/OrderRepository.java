package com.skipthedishes.api.repositories;

import com.skipthedishes.api.entities.Order;
import com.skipthedishes.api.entities.Restaurant;
import com.skipthedishes.api.repositories.order.OrderRepositoryQuery;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String>,OrderRepositoryQuery{

}