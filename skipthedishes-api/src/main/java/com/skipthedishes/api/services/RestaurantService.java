package com.skipthedishes.api.services;

import com.skipthedishes.api.entities.CategoriesEnum;
import com.skipthedishes.api.entities.Dish;
import com.skipthedishes.api.entities.Restaurant;
import com.skipthedishes.api.entities.TagsEnum;
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

    public Restaurant update(String id, Restaurant restaurant) {
        Restaurant restaurantRecovered = findById(id);
        BeanUtils.copyProperties(restaurant, restaurantRecovered, "id");
        return restaurantRepository.save(restaurantRecovered);
    }

    public List<Restaurant> findByCategoryAndTag(CategoriesEnum category, TagsEnum tag) {
        return restaurantRepository.findByCategoryAndTag(category, tag);
    }

    private Restaurant findById(String id) {
        Restaurant restaurant = restaurantRepository.findOne(id);
        if (restaurant == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return restaurant;
    }

}
