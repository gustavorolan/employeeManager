package com.ciss.employeeManager.service.employee.impl;

import com.ciss.employeeManager.dto.response.EmployeeResponse;
import com.ciss.employeeManager.exception.EmployeeNotFoundException;
import com.ciss.employeeManager.mapper.EmployeeMapper;
import com.ciss.employeeManager.model.EmployeeEntity;
import com.ciss.employeeManager.model.UserAccountEntity;
import com.ciss.employeeManager.repository.EmployeeRepository;
import com.ciss.employeeManager.security.FindUserAuthenticatedService;
import com.ciss.employeeManager.service.employee.FindEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindEmployeeServiceImpl implements FindEmployeeService {

    private final EmployeeRepository employeeRepository;

    private final FindUserAuthenticatedService findUserAuthenticatedService;

    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponse getResponseById(Long id) {
        EmployeeEntity employeeEntity = getEntityById(id);
        return employeeMapper.toResponse(employeeEntity);
    }

    @Override
    public EmployeeEntity getEntityById(Long id){
        UserAccountEntity user = findUserAuthenticatedService.getUser();
        return employeeRepository.findByUserIdAndEmployeeId(user.getId(), id)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public List<EmployeeResponse> getEmployeesFromUser(){
        UserAccountEntity user = findUserAuthenticatedService.getUser();
        List<EmployeeEntity> employeeEntityList = employeeRepository.findByUserId(user.getId());
        return employeeMapper.toResponse(employeeEntityList);
    }
}
