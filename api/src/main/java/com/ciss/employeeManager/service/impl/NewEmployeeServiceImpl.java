package com.ciss.employeeManager.service.impl;

import com.ciss.employeeManager.dto.request.NewEmployeeRequest;
import com.ciss.employeeManager.mapper.EmployeeMapper;
import com.ciss.employeeManager.model.EmployeeEntity;
import com.ciss.employeeManager.repository.EmployeeRepository;
import com.ciss.employeeManager.service.NewEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NewEmployeeServiceImpl implements NewEmployeeService {

    private final EmployeeMapper employeeMapper;

    private final EmployeeRepository employeeRepository;

    @Override
    public Long create(NewEmployeeRequest newEmployeeRequest) {
        EmployeeEntity entity = employeeMapper.toEntity(newEmployeeRequest);
        EmployeeEntity save = employeeRepository.save(entity);
        return save.getId();
    }
}
