package com.skipthedishes.api.services;

import com.skipthedishes.api.entities.Order;
import com.skipthedishes.api.entities.PaymentMethodsEnum;
import com.skipthedishes.api.exceptions.InvalidOrderTotalException;

import java.util.List;

public interface OrderManagementService {

    Order saveOrder(Order order);

    Boolean finishOrder(String id, PaymentMethodsEnum paymentMethod) throws InvalidOrderTotalException;

    Order findOrder(String id);

    List<Order> findOrdersByCustomer(String customerId);

    void cancelOrder(String id);
}
