package com.hexaware.fastXBus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TripsNotFoundException extends ResponseStatusException{

	
	public TripsNotFoundException(HttpStatus status,String msg) {
		super(status,msg);
	}
}
