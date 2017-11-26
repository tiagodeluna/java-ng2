package com.skipthedishes.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "valueInDishCoins" })
public interface PayableWithDishCoins {

    Double RATE = 10.0;

    Double getValueInDishCoins();

    default Double convert(Double amount) {
        return amount != null ? amount*RATE : null;
    }
}
