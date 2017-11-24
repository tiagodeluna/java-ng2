package com.skipthedishes.api.services;

import com.skipthedishes.api.entities.Order;
import com.skipthedishes.api.entities.PaymentMethodsEnum;
import org.springframework.stereotype.Service;

@Service
public interface OrderManagementService {

    Order save(Order order);

    //Order find(String id);

    Boolean finishOrder(String id, PaymentMethodsEnum paymentMethod);

    void cancelOrder(String id);
}
