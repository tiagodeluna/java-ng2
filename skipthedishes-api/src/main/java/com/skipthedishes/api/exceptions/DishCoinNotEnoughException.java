package com.skipthedishes.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = DishCoinNotEnoughException.MESSAGE)
public class DishCoinNotEnoughException extends Exception {

    private static final long serialVersionUID = -8992657892942548850L;

    protected static final String MESSAGE = "Dish coin not enough";

    public DishCoinNotEnoughException(Integer value) {
        super(String.format("%s: %s", DishCoinNotEnoughException.MESSAGE, value));
    }

}
