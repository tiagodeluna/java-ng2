package com.skipthedishes.api.repositories.order;

import com.skipthedishes.api.entities.Order;

import java.util.List;

public interface OrderRepositoryQuery {

    List<Order> findOrderNotReviewed(String customerId);

}
