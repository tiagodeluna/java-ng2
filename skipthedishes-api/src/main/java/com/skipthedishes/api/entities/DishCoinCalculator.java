package com.skipthedishes.api.entities;

public class DishCoinCalculator {

    private static final Double RATE = 25.0;

    public static Double convert(Double amount) {
        return amount != null ? amount * RATE : null;
    }
}
