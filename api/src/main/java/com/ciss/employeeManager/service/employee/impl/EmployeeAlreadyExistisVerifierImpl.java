package com.ciss.employeeManager.service.employee.impl;

import com.ciss.employeeManager.dto.request.NewEmployeeRequest;
import com.ciss.employeeManager.exception.EmployeeAlreadyExistsException;
import com.ciss.employeeManager.security.FindUserAuthenticatedService;
import com.ciss.employeeManager.service.employee.NewEmployeeVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmployeeAlreadyExistisVerifierImpl implements NewEmployeeVerifier {

    private final FindUserAuthenticatedService findUserAuthenticatedService;
    @Override
    public void verify(NewEmployeeRequest newEmployeeRequest) {

        boolean isEmailUsed = findUserAuthenticatedService.getUser().getEmployeeList()
                .stream()
                .anyMatch(employeeEntity -> Objects.equals(employeeEntity.getEmail(), newEmployeeRequest.getEmail()));

        if (isEmailUsed) throw new EmployeeAlreadyExistsException();
    }
}
