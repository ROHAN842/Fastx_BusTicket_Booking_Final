package com.hexaware.fastXBus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BusesNotFoundException  extends ResponseStatusException {
	
	public  BusesNotFoundException(HttpStatus status,String msg) {
		super(status,msg);
	}
}


