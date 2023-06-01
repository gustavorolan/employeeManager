package com.ciss.employeeManager.service.employee.impl;

import com.ciss.employeeManager.dto.response.EmployeeResponse;
import com.ciss.employeeManager.mapper.EmployeeMapper;
import com.ciss.employeeManager.model.EmployeeEntity;
import com.ciss.employeeManager.model.UserAccountEntity;
import com.ciss.employeeManager.repository.EmployeeRepository;
import com.ciss.employeeManager.security.FindUserAuthenticatedService;
import com.ciss.employeeManager.utils.factory.EmployeeFactory;
import com.ciss.employeeManager.utils.factory.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

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

    @Test
    void getResponseById() {
        EmployeeEntity employeeEntity = EmployeeFactory.getEntity();
        UserAccountEntity userAccountEntity = UserAccountFactory.getEntity(List.of(employeeEntity));
        EmployeeResponse employeeResponse = EmployeeFactory.getEmployeeResponse();

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
        UserAccountEntity userAccountEntity = UserAccountFactory.getEntityWithoutEmployee();
        EmployeeEntity employeeEntity = EmployeeFactory.getEntity();

        Mockito.when(findUserAuthenticatedService.getUser()).thenReturn(userAccountEntity);
        Mockito.when(employeeRepository.findByUserIdAndEmployeeId(userAccountEntity.getId(), employeeEntity.getId()))
                .thenReturn(Optional.of(employeeEntity));


        EmployeeEntity result = findEmployeeService.getEntityById(employeeEntity.getId());

        Mockito.verify(findUserAuthenticatedService).getUser();
        Mockito.verify(employeeRepository).findByUserIdAndEmployeeId(userAccountEntity.getId(), employeeEntity.getId());

        Assertions.assertEquals(employeeEntity, result);

        Mockito.verifyNoMoreInteractions(
                findUserAuthenticatedService,
                employeeRepository
        );
    }

    @Test
    void getEmployeesFromUser() {
        int page = 0;
        PageRequest pageRequest = PageRequest.of(page, 10);
        UserAccountEntity userAccountEntity = UserAccountFactory.getEntityWithoutEmployee();
        EmployeeEntity employeeEntity = EmployeeFactory.getEntity();
        EmployeeResponse employeeResponse = EmployeeFactory.getEmployeeResponse();
        Page<EmployeeEntity> employeeEntityPage = new PageImpl<>(List.of(employeeEntity));
        Page<EmployeeResponse> expected = new PageImpl<>(List.of(employeeResponse));

        Mockito.when(findUserAuthenticatedService.getUser()).thenReturn(userAccountEntity);
        Mockito.when(employeeRepository.findByUserId(userAccountEntity.getId(),pageRequest))
                .thenReturn(employeeEntityPage);
        Mockito.when(employeeMapper.toResponse(employeeEntity)).thenReturn(employeeResponse);

        Page<EmployeeResponse> result = findEmployeeService.getEmployeesFromUser(page);

        Mockito.verify(findUserAuthenticatedService).getUser();
        Mockito.verify(employeeRepository).findByUserId(userAccountEntity.getId(),pageRequest);
        Mockito.verify(employeeMapper).toResponse(employeeEntity);

        Assertions.assertEquals(expected, result);

        Mockito.verifyNoMoreInteractions(
                findUserAuthenticatedService,
                employeeRepository,
                employeeMapper
        );
    }
}