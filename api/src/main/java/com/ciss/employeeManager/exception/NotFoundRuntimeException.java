package com.ciss.employeeManager.exception;

public class NotFoundRuntimeException extends RuntimeException {
    public NotFoundRuntimeException(String message) {
        super(message);
    }
}
