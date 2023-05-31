package com.ciss.employeeManager.service.employee.impl;

import com.ciss.employeeManager.model.EmployeeEntity;
import com.ciss.employeeManager.repository.EmployeeRepository;
import com.ciss.employeeManager.service.employee.DeleteEmployeeService;
import com.ciss.employeeManager.service.employee.FindEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class DeleteEmployeeServiceImpl implements DeleteEmployeeService {

    private final FindEmployeeService findEmployeeService;

    private final EmployeeRepository employeeRepository;

    @Override
    public void delete(Long id) {
        EmployeeEntity employeeEntity = findEmployeeService.getEntityById(id);
        employeeRepository.delete(employeeEntity);
    }
}
