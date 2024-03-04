package com.hexaware.fastXBus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PaymentHistoryNotFoundException extends ResponseStatusException{


	public PaymentHistoryNotFoundException(HttpStatus status,String msg) {
		super(status,msg);
	}
}
