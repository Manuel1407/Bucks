package com.decagon.swisspay.infrastructure.error_handler;

public class AuthenticationFailedException extends RuntimeException{
    public AuthenticationFailedException(String message) {
        super(message);
    }
}
