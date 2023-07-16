package com.decagon.swisspay.infrastructure.error_handler;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
