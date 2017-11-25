package com.skipthedishes.api.repositories;

import com.skipthedishes.api.entities.Dish;
import com.skipthedishes.api.repositories.dish.DishRepositoryQuery;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DishRepository extends MongoRepository<Dish, String>, DishRepositoryQuery {

    public List<Dish> findAllByRestaurantId(String restaurantId);

}
