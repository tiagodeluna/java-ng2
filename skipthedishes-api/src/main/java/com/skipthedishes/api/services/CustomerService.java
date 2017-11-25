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

    public Customer addFavoriteDish(String customerId, String dishId) {
        Customer customerRecovered = customerRepository.findOne(customerId);
        if (customerRecovered == null) {
            throw new EmptyResultDataAccessException(1);
        }
        customerRecovered.getFavoriteDishes().add(dishId);
        return customerRepository.save(customerRecovered);
    }

    public Customer removeFavoriteDish(String customerId, String dishId) {
        Customer customerRecovered = customerRepository.findOne(customerId);
        if (customerRecovered == null) {
            throw new EmptyResultDataAccessException(1);
        }
        customerRecovered.getFavoriteDishes().remove(dishId);
        return customerRepository.save(customerRecovered);
    }

    public Customer addFavoriteRestaurant(String customerId, String restaurantId) {
        Customer customerRecovered = customerRepository.findOne(customerId);
        if (customerRecovered == null) {
            throw new EmptyResultDataAccessException(1);
        }
        customerRecovered.getFavoriteRestaurants().add(restaurantId);
        return customerRepository.save(customerRecovered);
    }

    public Customer removeFavoriteRestaurant(String customerId, String restaurantId) {
        Customer customerRecovered = customerRepository.findOne(customerId);
        if (customerRecovered == null) {
            throw new EmptyResultDataAccessException(1);
        }
        customerRecovered.getFavoriteRestaurants().remove(restaurantId);
        return customerRepository.save(customerRecovered);
    }
}
