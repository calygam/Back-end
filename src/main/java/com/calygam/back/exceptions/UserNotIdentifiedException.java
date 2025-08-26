package com.calygam.back.exceptions;

public class UserNotIdentifiedException extends RuntimeException {

	public UserNotIdentifiedException(String message) {
		super(message);
	}
}
