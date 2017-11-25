package com.skipthedishes.api.services;

import com.skipthedishes.api.entities.Customer;
import com.skipthedishes.api.entities.Dish;
import com.skipthedishes.api.entities.Restaurant;
import com.skipthedishes.api.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer favorite(String customerId, Dish dish) {
        Customer customerRecovered = customerRepository.findOne(customerId);
        if (customerRecovered == null) {
            throw new EmptyResultDataAccessException(1);
        }
        customerRecovered.getFavoriteDishes().add(dish);
        return customerRepository.save(customerRecovered);
    }

    public Customer favorite(String customerId, Restaurant restaurant) {
        Customer customerRecovered = customerRepository.findOne(customerId);
        if (customerRecovered == null) {
            throw new EmptyResultDataAccessException(1);
        }
        customerRecovered.getFavoriteRestaurants().add(restaurant);
        return customerRepository.save(customerRecovered);
    }
}
