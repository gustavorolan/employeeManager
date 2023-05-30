package com.ciss.employeeManager.controller;

import com.ciss.employeeManager.exception.BadRequestRuntimeException;
import com.ciss.employeeManager.exception.NotFoundRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(
            NotFoundRuntimeException.class
    )
    public ResponseEntity<String> notFoundedHandler(NotFoundRuntimeException exception)
    {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(
            BadRequestRuntimeException.class
    )
    public ResponseEntity<String> badRequestHandler(BadRequestRuntimeException exception) {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
