package com.skipthedishes.api.repositories.restaurant;

import com.skipthedishes.api.entities.Restaurant;

import java.util.List;

/**
 * Created by igorhara on 23/11/2017.
 */
public interface RestaurantRepositoryQuery {

    List<Restaurant> find(String text,int offset,int size);

}
