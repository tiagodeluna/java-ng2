package com.skipthedishes.api.repositories.dish;

import com.skipthedishes.api.entities.Dish;

import java.util.List;

public interface DishRepositoryQuery  {

    List<Dish> find(String idRestaurant, String text);

}
