package com.ciss.employeeManager.exception;

public class EmployeeAlreadyExistsException extends BadRequestRuntimeException {
    public EmployeeAlreadyExistsException() {
        super("This employee already exists!");
    }
}
