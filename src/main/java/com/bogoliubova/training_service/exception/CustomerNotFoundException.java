package com.bogoliubova.training_service.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String massage) {
        super(massage);
    }
}
