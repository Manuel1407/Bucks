package com.decagon.swisspay.infrastructure.error_handler;

public class NotificationNotFoundException extends RuntimeException{
    public NotificationNotFoundException(String message) {
        super(message);
    }
}
