package com.ciss.employeeManager.service.impl;

import com.ciss.employeeManager.dto.response.EmployeeResponse;
import com.ciss.employeeManager.mapper.EmployeeMapper;
import com.ciss.employeeManager.model.EmployeeEntity;
import com.ciss.employeeManager.model.UserAccountEntity;
import com.ciss.employeeManager.repository.EmployeeRepository;
import com.ciss.employeeManager.security.FindUserAuthenticatedService;
import com.ciss.employeeManager.service.employee.impl.FindEmployeeServiceImpl;
import com.ciss.employeeManager.utils.factory.EmployeeFactory;
import com.ciss.employeeManager.utils.factory.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FindEmployeeServiceImplTest {

    @InjectMocks
    private FindEmployeeServiceImpl findEmployeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private FindUserAuthenticatedService findUserAuthenticatedService;

    @Mock
    private EmployeeMapper employeeMapper;

    private final EmployeeEntity employeeEntity = EmployeeFactory.getEntity();
    private final UserAccountEntity userAccountEntity = UserAccountFactory.getEntity(List.of(employeeEntity));
    private final EmployeeResponse employeeResponse = EmployeeFactory.getEmployeeResponse();

    @Test
    void getResponseById() {
        Mockito.when(findUserAuthenticatedService.getUser()).thenReturn(userAccountEntity);
        Mockito.when(employeeRepository
                        .findByUserIdAndEmployeeId(userAccountEntity.getId(), employeeEntity.getId()))
                .thenReturn(Optional.of(employeeEntity));
        Mockito.when(employeeMapper.toResponse(employeeEntity)).thenReturn(employeeResponse);

        EmployeeResponse result = findEmployeeService.getResponseById(employeeEntity.getId());

        Mockito.verify(findUserAuthenticatedService).getUser();
        Mockito.verify(employeeRepository)
                .findByUserIdAndEmployeeId(userAccountEntity.getId(), employeeEntity.getId());
        Mockito.verify(employeeMapper).toResponse(employeeEntity);

        Assertions.assertEquals(employeeResponse, result);

        Mockito.verifyNoMoreInteractions(
                findUserAuthenticatedService,
                employeeRepository,
                employeeMapper
        );
    }

    @Test
    void getEntityById() {
    }

    @Test
    void getEmployeesFromUser() {
    }
}