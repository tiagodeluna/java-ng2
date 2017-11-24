package com.skipthedishes.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = InvalidOrderTotalException.MESSAGE)
public class InvalidOrderTotalException extends Exception {

	private static final long serialVersionUID = -8992657892942548850L;
	
	public static final String MESSAGE = "Order total is invalid";
	
	public InvalidOrderTotalException(Double value) {
		super(String.format("%s: %s", InvalidOrderTotalException.MESSAGE, value));
	}
    
}
