package com.mastery.task.exception;

public class NoSuchDepartmentException extends RuntimeException {

    public NoSuchDepartmentException() {
        super();
    }

    public NoSuchDepartmentException(String message) {
        super(message);
    }

    public NoSuchDepartmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
