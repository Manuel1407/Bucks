package com.decagon.reward_your_teacher.infrastructure.error_handler;

public class EntityAlreadyExistException extends RuntimeException {
    public EntityAlreadyExistException(String message) {
        super(message);
    }
}
