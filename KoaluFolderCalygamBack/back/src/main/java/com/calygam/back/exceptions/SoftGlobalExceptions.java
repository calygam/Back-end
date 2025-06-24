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
	@ExceptionHandler(UnauthorizedAcessUserException.class)
	public ResponseEntity<String> GlobalUnauthorizedAcessUserException(UnauthorizedAcessUserException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(UserNotIdentifiedException.class)
	public ResponseEntity<String> GlobalUserNotIdentifiedException(UserNotIdentifiedException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ExcededMaxDelimiter.class)
	public ResponseEntity<String> GlobalExcededMaxDelimiter(ExcededMaxDelimiter ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
	
}
