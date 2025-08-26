package com.calygam.back.exceptions;

public class UnauthorizedAcessUserException extends RuntimeException {
	
	public UnauthorizedAcessUserException(String unauthorizedMessage) {
		super(unauthorizedMessage);
	}
}
