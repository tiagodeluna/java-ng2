package com.skipthedishes.api.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by igorhara on 23/11/2017.
 */
@Getter
@Setter
public class Item {

    private int amount;

    private Integer reviewRating;

    private Dish dish;

    private Order order;
}
