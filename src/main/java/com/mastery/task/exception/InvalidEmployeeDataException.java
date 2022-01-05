package com.mastery.task.exception;

public class InvalidEmployeeDataException extends RuntimeException {

    public InvalidEmployeeDataException() {
        super();
    }

    public InvalidEmployeeDataException(String message) {
        super(message);
    }

    public InvalidEmployeeDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
