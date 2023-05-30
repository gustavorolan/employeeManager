package com.ciss.employeeManager.service.impl;

import com.ciss.employeeManager.dto.request.UpdateEmployeeRequest;
import com.ciss.employeeManager.model.EmployeeEntity;
import com.ciss.employeeManager.repository.EmployeeRepository;
import com.ciss.employeeManager.service.FindEmployeeService;
import com.ciss.employeeManager.service.UpdateEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UpdateEmployeeServiceImpl implements UpdateEmployeeService {

    private final FindEmployeeService findEmployeeService;

    private final EmployeeRepository employeeRepository;

    @Override
    public void put(UpdateEmployeeRequest request) {
        EmployeeEntity employee = findEmployeeService.getEntityById(request.getEmployeeId());
        EmployeeEntity employeeEdited = EmployeeEntity.builder()
                .id(employee.getId())
                .email(!Objects.equals(request.getEmail(), "") ? request.getEmail() : employee.getEmail())
                .surname(!Objects.equals(request.getSurname(), "") ? request.getSurname() : employee.getSurname())
                .pisNumber(!Objects.equals(request.getPisNumber(), 0L) ? request.getPisNumber() : employee.getPisNumber())
                .name(!Objects.equals(request.getName(), "") ? request.getName() : employee.getName())
                .build();

        employeeRepository.save(employeeEdited);
    }
}
