package com.ciss.employeeManager.exception;

public class UserAlreadyExistsException extends BadRequestRuntimeException {
    public UserAlreadyExistsException() {
        super("This user already exists!");
    }
}
