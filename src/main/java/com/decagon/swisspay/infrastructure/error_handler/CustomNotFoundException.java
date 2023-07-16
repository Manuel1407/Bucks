package com.decagon.swisspay.infrastructure.error_handler;

public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String message) {
        super(message);
    }
}
