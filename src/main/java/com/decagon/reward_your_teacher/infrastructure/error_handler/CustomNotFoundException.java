package com.decagon.reward_your_teacher.infrastructure.error_handler;

public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String message) {
        super(message);
    }
}
