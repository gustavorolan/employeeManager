package com.ciss.employeeManager.controller;

import com.ciss.employeeManager.exception.BadRequestRuntimeException;
import com.ciss.employeeManager.exception.NotFoundRuntimeException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(
            NotFoundRuntimeException.class
    )
    public ResponseEntity<String> notFoundedHandler(NotFoundRuntimeException exception) {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(
            BadRequestRuntimeException.class
    )
    public ResponseEntity<String> badRequestHandler(BadRequestRuntimeException exception) {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public ResponseEntity<String> badRequestHandler(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .distinct()
                .collect(Collectors.toList())
                .toString();
        return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
    }
}
