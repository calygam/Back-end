package com.calygam.back.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SoftGlobalExceptions {
	
	@ExceptionHandler(UserAlreadExistsException.class)
	public ResponseEntity<String> GlobalUserAlreadExistsException(UserAlreadExistsException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
