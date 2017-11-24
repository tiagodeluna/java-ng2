package com.skipthedishes.api.services.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skipthedishes.api.entities.Customer;
import com.skipthedishes.api.entities.Order;
import com.skipthedishes.api.entities.OrderStatusEnum;
import com.skipthedishes.api.entities.PaymentMethodsEnum;
import com.skipthedishes.api.exceptions.InvalidOrderTotalException;
import com.skipthedishes.api.repositories.CustomerRepository;
import com.skipthedishes.api.repositories.OrderRepository;
import com.skipthedishes.api.services.OrderManagementService;

@Service
public class OrderManagementServiceImpl implements OrderManagementService {

    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public OrderManagementServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Order saveOrder(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public Boolean finishOrder(String id, PaymentMethodsEnum paymentMethod) throws InvalidOrderTotalException {
        Boolean successfulPayment;

        Order order = this.orderRepository.findOne(id);
        
        if (order == null) {
        	return Boolean.FALSE;
        }
        
        //Check if order total is valid
        if (order.getTotal() == null || order.getTotal() <= 0) {
        	throw new InvalidOrderTotalException();
        }

        Customer customer = order.getCustomer();

        /* If the customer paid with DishCoins, then his balance must be reduced */
        if (paymentMethod.equals(PaymentMethodsEnum.DISH_POINTS)) {
            successfulPayment = customer.spendDishCoins(order.getTotal());
        } else {
            //If the payment was made with cash/credit, then the customer accumulates points
            customer.accumulateDishCoins(order.getTotal());
            /* Since we have decided to abstract the cash payment process,
            we are considering that it will always be successful. */
            successfulPayment = Boolean.TRUE;
        }

        if (successfulPayment) {
            order.setStatus(OrderStatusEnum.PAYMENT_CONFIRMED);
            this.customerRepository.save(customer);
        } else {
            order.setStatus(OrderStatusEnum.PAYMENT_FAILURE);
        }

        this.orderRepository.save(order);

        return successfulPayment;
    }

    @Override
    public Order findOrder(String id) {
        return this.orderRepository.findOne(id);
    }

    @Override
    public List<Order> findOrdersByCustomer(String customerId) {
        return this.orderRepository.findByCustomer_Id(customerId);
    }

    @Override
    public void cancelOrder(String id) {
        Order order = this.orderRepository.findOne(id);
        order.setStatus(OrderStatusEnum.CANCELED);
        this.orderRepository.save(order);
    }
}
