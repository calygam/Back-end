package com.calygam.back.exceptions;

public class UserAlreadExistsException extends RuntimeException {
	
	public UserAlreadExistsException(String message) {
		super(message);
	}
}
