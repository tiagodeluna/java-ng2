package com.skipthedishes.api.services.order;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skipthedishes.api.entities.Customer;
import com.skipthedishes.api.entities.Order;
import com.skipthedishes.api.entities.OrderStatusEnum;
import com.skipthedishes.api.entities.PaymentMethodsEnum;
import com.skipthedishes.api.exceptions.InvalidOrderTotalException;
import com.skipthedishes.api.repositories.CustomerRepository;
import com.skipthedishes.api.repositories.OrderRepository;
import com.skipthedishes.api.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Order save(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public Order update(String id, Order order) {
        Order orderRecovered = findById(id);
        BeanUtils.copyProperties(order, orderRecovered, "id");
        return orderRepository.save(orderRecovered);
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
        	throw new InvalidOrderTotalException(order.getTotal());
        }

        Customer customer = this.customerRepository.findOne(order.getCustomerId());

        /* If the customer paid with DishCoins, then his balance must be reduced */
        if (paymentMethod.equals(PaymentMethodsEnum.DISH_COINS)) {
            successfulPayment = customer.spendDishCoins(order.getTotalInDishCoins());
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
    public Order findById(String id) {
        return this.orderRepository.findOne(id);
    }

    @Override
    public List<Order> findByCustomerId(String customerId) {
        return this.orderRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

}
