package com.bogoliubova.training_service.exception;

public class CustomerUpdateException extends RuntimeException {
    public CustomerUpdateException(String message) {
        super(message);
    }
}
