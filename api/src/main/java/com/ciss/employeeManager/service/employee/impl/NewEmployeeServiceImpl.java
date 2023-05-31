package com.ciss.employeeManager.service.employee.impl;

import com.ciss.employeeManager.dto.request.NewEmployeeRequest;
import com.ciss.employeeManager.mapper.EmployeeMapper;
import com.ciss.employeeManager.model.EmployeeEntity;
import com.ciss.employeeManager.model.UserAccountEntity;
import com.ciss.employeeManager.repository.EmployeeRepository;
import com.ciss.employeeManager.security.FindUserAuthenticatedService;
import com.ciss.employeeManager.service.employee.NewEmployeeService;
import com.ciss.employeeManager.service.employee.NewEmployeeVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NewEmployeeServiceImpl implements NewEmployeeService {

    private final EmployeeMapper employeeMapper;

    private final EmployeeRepository employeeRepository;

    private final FindUserAuthenticatedService findUserAuthenticatedService;

    private final List<NewEmployeeVerifier> newEmployeeVerifierList;
    @Override
    public Long create(NewEmployeeRequest newEmployeeRequest) {
        verify(newEmployeeRequest);
        UserAccountEntity user = findUserAuthenticatedService.getUser();
        EmployeeEntity entity = employeeMapper.toEntity(newEmployeeRequest, user);
        EmployeeEntity save = employeeRepository.save(entity);
        return save.getId();
    }

    private void verify(NewEmployeeRequest newEmployeeRequest) {
        newEmployeeVerifierList.forEach(newEmployeeVerifier -> newEmployeeVerifier.verify(newEmployeeRequest));
    }
}
