package com.ciss.employeeManager.service.employee;

import com.ciss.employeeManager.dto.request.NewEmployeeRequest;

public interface NewEmployeeVerifier {
    void verify(NewEmployeeRequest newEmployeeRequest);
}
