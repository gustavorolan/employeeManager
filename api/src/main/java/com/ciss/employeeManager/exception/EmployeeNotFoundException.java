package com.ciss.employeeManager.exception;

public class EmployeeNotFoundException extends NotFoundRuntimeException {
    public EmployeeNotFoundException() {
        super("Employee not found!");
    }
}
