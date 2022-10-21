package com.decagon.reward_your_teacher.infrastructure.error_handler;

public class AuthenticationFailedException extends RuntimeException{
    public AuthenticationFailedException(String message) {
        super(message);
    }
}
