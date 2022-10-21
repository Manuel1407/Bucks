package com.decagon.reward_your_teacher.infrastructure.error_handler;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
