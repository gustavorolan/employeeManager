package com.ciss.employeeManager.service.employee;

import com.ciss.employeeManager.dto.response.EmployeeResponse;
import com.ciss.employeeManager.model.EmployeeEntity;
import org.springframework.data.domain.Page;

public interface FindEmployeeService {
    EmployeeResponse getResponseById(Long id);

    EmployeeEntity getEntityById(Long id);

    Page<EmployeeResponse> getEmployeesFromUser(Integer page);
}
