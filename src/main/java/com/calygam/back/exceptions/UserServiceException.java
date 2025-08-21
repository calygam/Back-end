package com.calygam.back.exceptions;
//ver que ver como tá lá na routine antes CAIO 
public class UserServiceException extends Exception {
    public UserServiceException(String message) {
        super(message);
    }
    
    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
