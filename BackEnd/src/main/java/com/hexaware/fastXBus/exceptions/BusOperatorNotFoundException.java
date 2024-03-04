package com.hexaware.fastXBus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BusOperatorNotFoundException extends ResponseStatusException {
	
	public BusOperatorNotFoundException(HttpStatus status,String msg) {
		super(status,msg);
	}

}
