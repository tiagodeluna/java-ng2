package com.skipthedishes.api.services;

import com.skipthedishes.api.entities.Customer;
import com.skipthedishes.api.exceptions.DishCoinNotEnoughException;
import com.skipthedishes.api.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer addFavoriteDish(String customerId, String dishId) {
        Customer customer = getCustomer(customerId);
        customer.getFavoriteDishes().add(dishId);
        return customerRepository.save(customer);
    }

    public Customer removeFavoriteDish(String customerId, String dishId) {
        Customer customer = getCustomer(customerId);
        customer.getFavoriteDishes().remove(dishId);
        return customerRepository.save(customer);
    }

    public Customer addFavoriteRestaurant(String customerId, String restaurantId) {
        Customer customer = getCustomer(customerId);
        customer.getFavoriteRestaurants().add(restaurantId);
        return customerRepository.save(customer);
    }

    public Customer removeFavoriteRestaurant(String customerId, String restaurantId) {
        Customer customerRecovered = getCustomer(customerId);
        customerRecovered.getFavoriteRestaurants().remove(restaurantId);
        return customerRepository.save(customerRecovered);
    }

    public Customer decreaseDishCoins(String id, String value) throws DishCoinNotEnoughException {
        try {
            Integer dishCoins = Integer.valueOf(value);
            Customer customer = getCustomer(id);

            Integer dishCoinCalc = customer.getDishCoin() - dishCoins;

            if (dishCoinCalc < 0) {
                throw new DishCoinNotEnoughException(dishCoins);
            }

            customer.setDishCoin(dishCoinCalc);

            return customerRepository.save(customer);
        }catch (NumberFormatException e) {
            throw new NumberFormatException("Dish Coins need to be a value.");
        }
    }

    public Customer addDishCoins(String id, String value) throws DishCoinNotEnoughException {
        try {
            Customer customer = getCustomer(id);
            customer.setDishCoin(Integer.valueOf(value));
            return customerRepository.save(customer);
        }catch (NumberFormatException e) {
            throw new NumberFormatException("Dish Coins need to be a value.");
        }
    }

    private Customer getCustomer(String customerId) {
        Customer customerRecovered = customerRepository.findOne(customerId);
        if (customerRecovered == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return customerRecovered;
    }
}
