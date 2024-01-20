package com.bogoliubova.training_service.exception;

public class BookEx extends RuntimeException{

    public BookEx() {
    }

    public BookEx(String message) {
        super(message);
    }

    public BookEx(String message, Throwable cause) {
        super(message, cause);
    }

    public BookEx(Throwable cause) {
        super(cause);
    }

    public BookEx(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
