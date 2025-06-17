package com.calygam.back.exceptions;

public class GoogleAuthException extends Exception {
    public GoogleAuthException(String message) {
        super(message);
    }
    
    public GoogleAuthException(String message, Throwable cause) {
        super(message, cause);
    }
}
