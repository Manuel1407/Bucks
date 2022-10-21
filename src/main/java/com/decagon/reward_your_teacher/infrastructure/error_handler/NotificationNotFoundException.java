package com.decagon.reward_your_teacher.infrastructure.error_handler;

public class NotificationNotFoundException extends RuntimeException{
    public NotificationNotFoundException(String message) {
        super(message);
    }
}
