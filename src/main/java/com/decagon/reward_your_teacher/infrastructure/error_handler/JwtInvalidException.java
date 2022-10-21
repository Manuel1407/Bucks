package com.decagon.reward_your_teacher.infrastructure.error_handler;

public class JwtInvalidException extends RuntimeException {
    public JwtInvalidException(String message) {
        super(message);
    }
}
