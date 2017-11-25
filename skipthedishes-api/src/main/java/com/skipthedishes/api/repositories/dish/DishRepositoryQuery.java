package com.skipthedishes.api.repositories.dish;

import com.skipthedishes.api.entities.CategoriesEnum;
import com.skipthedishes.api.entities.Dish;
import com.skipthedishes.api.entities.TagsEnum;

import java.util.List;

public interface DishRepositoryQuery  {

    List<Dish> find( String text,int offset, int size);

    List<Dish> findFavoriteByCustomerId(String customerId);
    List<Dish> findByCategoryAndTag(String restaurantId, CategoriesEnum category, TagsEnum tag);


}
