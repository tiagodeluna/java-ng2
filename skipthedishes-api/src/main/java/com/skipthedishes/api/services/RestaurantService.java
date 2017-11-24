package com.skipthedishes.api.services;

import com.skipthedishes.api.entities.Restaurant;
import com.skipthedishes.api.repositories.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant update(String id, Restaurant restaurant) {
        Restaurant restaurantRecovered = findById(id);
        BeanUtils.copyProperties(restaurant, restaurantRecovered, "id");
        return restaurantRepository.save(restaurantRecovered);
    }

    private Restaurant findById(String id) {
        Restaurant restaurant = restaurantRepository.findOne(id);
        if (restaurant == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return restaurant;
    }
}
