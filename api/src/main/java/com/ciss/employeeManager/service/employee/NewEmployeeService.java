package com.ciss.employeeManager.service.employee;

import com.ciss.employeeManager.dto.request.NewEmployeeRequest;

public interface NewEmployeeService {
     Long create(NewEmployeeRequest newEmployeeRequest);
}
