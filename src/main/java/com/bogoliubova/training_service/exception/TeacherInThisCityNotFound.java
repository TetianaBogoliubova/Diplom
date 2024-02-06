package com.bogoliubova.training_service.exception;

public class TeacherInThisCityNotFound extends RuntimeException {
    public TeacherInThisCityNotFound(String massage) {
        super(massage);
    }
}
