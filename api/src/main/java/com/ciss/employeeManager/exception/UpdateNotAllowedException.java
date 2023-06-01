package com.ciss.employeeManager.exception;

public class UpdateNotAllowedException extends NotFoundRuntimeException {
    public UpdateNotAllowedException() {
        super("Update not allowed!");
    }
}
