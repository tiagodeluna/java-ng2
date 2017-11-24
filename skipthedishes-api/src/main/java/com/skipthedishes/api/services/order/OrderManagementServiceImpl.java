package com.skipthedishes.api.services.order;

import com.skipthedishes.api.entities.Customer;
import com.skipthedishes.api.entities.Order;
import com.skipthedishes.api.entities.OrderStatusEnum;
import com.skipthedishes.api.repositories.OrderRepository;
import com.skipthedishes.api.services.OrderManagementService;
import com.skipthedishes.api.entities.PaymentMethodsEnum;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderManagementServiceImpl implements OrderManagementService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public Boolean finishOrder(String id, PaymentMethodsEnum paymentMethod) {
        Boolean successfulPayment;

        Order order = this.orderRepository.findOne(id);

        Customer customer = order.getCustomer();

        /* If the customer paid with DishCoins, then his balance must be reduced */
        if (paymentMethod.equals(PaymentMethodsEnum.DISH_POINTS)) {
            successfulPayment = customer.debitDishCoins(order.getTotal());
        } else {
            //If the payment was made with cash/credit, then the customer accumulates points
            customer.accumulateDishCoins(order.getTotal());
            /* Since we have decided to abstract the cash payment process,
            we are considering that it will always be successful. */
            successfulPayment = Boolean.TRUE;
        }

        if (successfulPayment) {
            order.setStatus(OrderStatusEnum.PAYMENT_CONFIRMED);
            //TODO Save customer
        } else {
            order.setStatus(OrderStatusEnum.PAYMENT_FAILURE);
        }

        this.orderRepository.save(order);

        return successfulPayment;
    }

    @Override
    public void cancelOrder(String id) {

    }
}
