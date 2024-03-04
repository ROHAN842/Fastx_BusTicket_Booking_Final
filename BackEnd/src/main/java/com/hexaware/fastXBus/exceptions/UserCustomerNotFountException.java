package com.hexaware.fastXBus.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
public class UserCustomerNotFountException extends ResponseStatusException {
	
	public  UserCustomerNotFountException(HttpStatus status,String msg) {
		super(status,msg);
	}

	
}
