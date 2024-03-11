package com.bogoliubova.training_service.exception;

public class JwtAuthenticationException extends RuntimeException {
    public JwtAuthenticationException(String massage) {
        super(massage);
    }

    }

