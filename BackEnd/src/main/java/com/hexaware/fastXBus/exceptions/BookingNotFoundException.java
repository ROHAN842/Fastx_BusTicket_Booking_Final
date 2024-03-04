package com.hexaware.fastXBus.exceptions;

import org.springframework.http.HttpStatus;

import org.springframework.web.server.ResponseStatusException;

public class BookingNotFoundException extends ResponseStatusException {
	


	public BookingNotFoundException(HttpStatus status,String msg) {
		super(status,msg);
	}
}
