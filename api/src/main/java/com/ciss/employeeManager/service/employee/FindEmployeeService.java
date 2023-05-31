package com.ciss.employeeManager.service.employee;

import com.ciss.employeeManager.dto.response.EmployeeResponse;
import com.ciss.employeeManager.model.EmployeeEntity;

import java.util.List;

public interface FindEmployeeService {
    EmployeeResponse getResponseById(Long id);

    EmployeeEntity getEntityById(Long id);

    List<EmployeeResponse> getEmployeesFromUser();
}
