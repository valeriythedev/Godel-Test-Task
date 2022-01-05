package com.mastery.task.exception.controllerhandler;

import com.mastery.task.exception.InvalidEmployeeDataException;
import com.mastery.task.exception.NoSuchDepartmentException;
import com.mastery.task.exception.NoSuchGenderException;
import com.mastery.task.exception.NoSuchRecordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice("com.mastery.task.rest")
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchRecordException.class)
    public ResponseEntity<ExceptionInfo> handleNoSuchRecordException(NoSuchRecordException exception) {
        ExceptionInfo info = new ExceptionInfo(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(info, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchDepartmentException.class)
    public ResponseEntity<ExceptionInfo> handleNoSuchDeparmentException(NoSuchDepartmentException exception) {
        ExceptionInfo info = new ExceptionInfo(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchGenderException.class)
    public ResponseEntity<ExceptionInfo> handleNoSuchGenderException(NoSuchGenderException exception) {
        ExceptionInfo info = new ExceptionInfo(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmployeeDataException.class)
    public ResponseEntity<ExceptionInfo> handleInvalidEmployeeDataException(InvalidEmployeeDataException exception) {
        ExceptionInfo info = new ExceptionInfo(exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
    }
}
