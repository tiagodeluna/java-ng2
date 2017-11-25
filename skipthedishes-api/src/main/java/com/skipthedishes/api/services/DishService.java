package com.skipthedishes.api.services;

import com.skipthedishes.api.entities.CategoriesEnum;
import com.skipthedishes.api.entities.Dish;
import com.skipthedishes.api.entities.Restaurant;
import com.skipthedishes.api.entities.TagsEnum;
import com.skipthedishes.api.repositories.DishRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public Dish update(String id, Dish restaurant) {
        Dish dishRecovered = findById(id);
        BeanUtils.copyProperties(restaurant, dishRecovered, "id");
        return dishRepository.save(dishRecovered);
    }

    public List<Dish> findByCategoryAndTag(String restaurantId, CategoriesEnum category, TagsEnum tag) {
        return dishRepository.findByCategoryAndTag(restaurantId, category, tag);
    }

    public Dish findById(String id) {
        Dish dish = dishRepository.findOne(id);
        if (dish == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return dish;
    }

    public List<Dish> findByRestaurantId(String restaurantId){
        return this.dishRepository.findAllByRestaurantId(restaurantId);
    }

}
