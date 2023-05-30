package com.ciss.employeeManager.service;

import com.ciss.employeeManager.dto.request.NewEmployeeRequest;

public interface NewEmployeeService {
     Long create(NewEmployeeRequest newEmployeeRequest);
}
