package com.ciss.employeeManager.service.employee.impl;

import com.ciss.employeeManager.dto.request.NewEmployeeRequest;
import com.ciss.employeeManager.mapper.EmployeeMapper;
import com.ciss.employeeManager.model.EmployeeEntity;
import com.ciss.employeeManager.model.UserAccountEntity;
import com.ciss.employeeManager.repository.EmployeeRepository;
import com.ciss.employeeManager.security.FindUserAuthenticatedService;
import com.ciss.employeeManager.service.employee.NewEmployeeVerifier;
import com.ciss.employeeManager.utils.factory.EmployeeFactory;
import com.ciss.employeeManager.utils.factory.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class NewEmployeeServiceImplTest {

    @InjectMocks
    private NewEmployeeServiceImpl newEmployeeService;

    @Mock
    private EmployeeMapper employeeMapper;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private FindUserAuthenticatedService findUserAuthenticatedService;

    @Mock
    private List<NewEmployeeVerifier> newEmployeeVerifierList;

    @Captor
    private ArgumentCaptor<EmployeeEntity> captor;

    @Test
    void create() {
        UserAccountEntity userAccountEntity = UserAccountFactory.getEntityWithoutEmployee();
        NewEmployeeRequest newEmployeeRequest = EmployeeFactory.getNewEmployeeRequest();
        EmployeeEntity employeeEntity = EmployeeFactory.getEntity(userAccountEntity);

        Mockito.when(findUserAuthenticatedService.getUser()).thenReturn(userAccountEntity);
        Mockito.when(employeeMapper.toEntity(newEmployeeRequest, userAccountEntity)).thenReturn(employeeEntity);
        Mockito.when(employeeRepository.save(employeeEntity)).thenReturn(employeeEntity);

        Long result = newEmployeeService.create(newEmployeeRequest);

        Mockito.verify(findUserAuthenticatedService).getUser();
        Mockito.verify(employeeMapper).toEntity(newEmployeeRequest, userAccountEntity);
        Mockito.verify(employeeRepository).save(captor.capture());

        Assertions.assertEquals(employeeEntity.getId(), result);
        Assertions.assertEquals(employeeEntity, captor.getValue());

        Mockito.verifyNoMoreInteractions(
                findUserAuthenticatedService,
                employeeMapper,
                employeeRepository
        );
    }
}