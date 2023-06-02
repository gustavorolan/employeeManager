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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FindEmployeeServiceImpl implements FindEmployeeService {

    private final static Integer MAX_ELEMENTS_PER_PAGE = 7;

    private final EmployeeRepository employeeRepository;

    private final FindUserAuthenticatedService findUserAuthenticatedService;

    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponse getResponseById(Long id) {
        EmployeeEntity employeeEntity = getEntityById(id);
        return employeeMapper.toResponse(employeeEntity);
    }

    @Override
    public EmployeeEntity getEntityById(Long id) {
        UserAccountEntity user = findUserAuthenticatedService.getUser();
        return employeeRepository.findByUserIdAndEmployeeId(user.getId(), id)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Page<EmployeeResponse> getEmployeesFromUser(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, MAX_ELEMENTS_PER_PAGE);
        UserAccountEntity user = findUserAuthenticatedService.getUser();
        Page<EmployeeEntity> employeeEntityPage = employeeRepository
                .findByUserId(user.getId(), pageRequest);
        return employeeEntityPage.map(employeeMapper::toResponse);
    }
}
