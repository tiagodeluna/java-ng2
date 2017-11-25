package com.skipthedishes.api.entities;

public interface PayableWithDishCoins {

    Double RATE = 25.0;

    Double getValueInDishCoins();

    default Double convert(Double amount) {
        return amount != null ? amount*RATE : null;
    }
}
