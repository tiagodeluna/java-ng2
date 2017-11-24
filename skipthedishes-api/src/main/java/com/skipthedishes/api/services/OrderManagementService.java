package com.skipthedishes.api.services;

import com.skipthedishes.api.entities.Order;
import com.skipthedishes.api.entities.PaymentMethodsEnum;
import org.springframework.stereotype.Service;

public interface OrderManagementService {

    Boolean finishOrder(String id, PaymentMethodsEnum paymentMethod);

    void cancelOrder(String id);
}
