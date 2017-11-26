package com.skipthedishes.api.services;

import com.skipthedishes.api.entities.Order;
import com.skipthedishes.api.entities.PaymentMethodsEnum;
import com.skipthedishes.api.exceptions.InvalidOrderTotalException;

import java.util.List;

public interface OrderService {

    List<Order> findByCustomerId(String customerId);

    List<Order> findAll();

    Order findById(String id);

    Boolean finishOrder(String id, PaymentMethodsEnum paymentMethod) throws InvalidOrderTotalException;

    Order saveAndFinishOrder(Order order) throws InvalidOrderTotalException;

    Order save(Order order);

    Order update(String id, Order order);

}
