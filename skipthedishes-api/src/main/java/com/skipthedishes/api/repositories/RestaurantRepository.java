package com.skipthedishes.api.repositories;

import com.skipthedishes.api.entities.Restaurant;
import com.skipthedishes.api.repositories.restaurant.RestaurantRepositoryQuery;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RestaurantRepository extends MongoRepository<Restaurant, String>, RestaurantRepositoryQuery {

    Restaurant findByName(String name);

    List<Restaurant> findByNameOrTags(String value);



}