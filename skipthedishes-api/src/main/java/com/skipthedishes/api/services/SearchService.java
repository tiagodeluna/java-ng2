package com.skipthedishes.api.services;

import com.skipthedishes.api.entities.Dish;
import com.skipthedishes.api.entities.Restaurant;
import com.skipthedishes.api.repositories.DishRepository;
import com.skipthedishes.api.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by igorhara on 24/11/2017.
 */
@Service
public class SearchService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private DishRepository dishRepository;


    /**
     * find all the dishes where the name or tags matches the textSearch passed.
     * @param textSearch
     * @return
     */
    public List<Dish> findDishesTextSearch(String textSearch){
       return null;
    }

    /**
     * find all the restaurants where matches the category passed and optionaly by matches a textSearch
     * @param category
     * @return
     */
    public List<Dish> findDishByCategoryAndtextSearch(String category,String textSearch){
        return null;
    }







    

}
