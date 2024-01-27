package com.bogoliubova.training_service.exception;

public class LocationNotFoundException extends RuntimeException{
    public LocationNotFoundException(String massage) {
        super(massage);
    }
}
