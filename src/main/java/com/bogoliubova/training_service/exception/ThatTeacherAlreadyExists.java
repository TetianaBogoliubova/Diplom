package com.bogoliubova.training_service.exception;

public class ThatTeacherAlreadyExists extends RuntimeException{
    public ThatTeacherAlreadyExists(String message) {
        super(message);
    }
}
