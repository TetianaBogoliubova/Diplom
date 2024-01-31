package com.bogoliubova.training_service.exception;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(String massage) {
        super(massage);
    }
}
