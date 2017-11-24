package com.skipthedishes.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Order total is invalid")
public class InvalidOrderTotalException extends Exception {

	private static final long serialVersionUID = -8992657892942548850L;

	public InvalidOrderTotalException() {
		super();
	}
    
}
