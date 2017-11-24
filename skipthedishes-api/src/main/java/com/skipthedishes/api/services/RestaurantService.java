package com.skipthedishes.api.services;

import com.skipthedishes.api.entities.Dish;
import com.skipthedishes.api.entities.Restaurant;
import com.skipthedishes.api.repositories.DishRepository;
import com.skipthedishes.api.repositories.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private DishRepository dishRepository;

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

    public List<Dish> findDishesByRestaurantId(String id){
        return this.dishRepository.findAllByIdRestaurant(id);
    }
}
