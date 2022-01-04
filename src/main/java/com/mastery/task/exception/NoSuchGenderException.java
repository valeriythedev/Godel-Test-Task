package com.mastery.task.exception;

public class NoSuchGenderException extends RuntimeException {

    public NoSuchGenderException() {
        super();
    }

    public NoSuchGenderException(String message) {
        super(message);
    }

    public NoSuchGenderException(String message, Throwable cause) {
        super(message, cause);
    }
}
