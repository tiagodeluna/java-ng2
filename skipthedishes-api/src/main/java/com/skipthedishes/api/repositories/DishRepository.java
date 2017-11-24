package com.skipthedishes.api.repositories;

import com.skipthedishes.api.entities.Dish;
import com.skipthedishes.api.repositories.dish.DishRepositoryQuery;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DishRepository  extends MongoRepository<Dish, String>, DishRepositoryQuery {

}
