package com.ciss.employeeManager.service.employee.impl;

import com.ciss.employeeManager.dto.request.UpdateEmployeeRequest;
import com.ciss.employeeManager.exception.UpdateNotAllowedException;
import com.ciss.employeeManager.model.EmployeeEntity;
import com.ciss.employeeManager.repository.EmployeeRepository;
import com.ciss.employeeManager.service.employee.FindEmployeeService;
import com.ciss.employeeManager.utils.factory.EmployeeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UpdateEmployeeServiceImplTest {

    @InjectMocks
    private UpdateEmployeeServiceImpl updateEmployeeService;

    @Mock
    private  FindEmployeeService findEmployeeService;

    @Mock
    private  EmployeeRepository employeeRepository;

    @Captor
    private ArgumentCaptor<EmployeeEntity> captor;

    @Test
    void update() {
        EmployeeEntity entity = EmployeeFactory.getEntity();
        EmployeeEntity expected = EmployeeFactory.getEntityUpdated();
        UpdateEmployeeRequest updateEmployeeRequest = EmployeeFactory.getUpdateEmployeeRequest();

        Mockito.when(findEmployeeService.getEntityById(updateEmployeeRequest.getEmployeeId())).thenReturn(entity);

        updateEmployeeService.update(updateEmployeeRequest);

        Mockito.verify(findEmployeeService).getEntityById(updateEmployeeRequest.getEmployeeId());
        Mockito.verify(employeeRepository).save(captor.capture());

        Assertions.assertEquals(expected, captor.getValue());

        Mockito.verifyNoMoreInteractions(findEmployeeService,employeeRepository);
    }

    @Test
    void updatePassingEmptyValues(){
        EmployeeEntity entity = EmployeeFactory.getEntity();
        UpdateEmployeeRequest updateEmployeeRequest =
                new UpdateEmployeeRequest(1L,"","","",0L);

        Mockito.when(findEmployeeService.getEntityById(updateEmployeeRequest.getEmployeeId())).thenReturn(entity);

        updateEmployeeService.update(updateEmployeeRequest);

        Mockito.verify(findEmployeeService).getEntityById(updateEmployeeRequest.getEmployeeId());
        Mockito.verify(employeeRepository).save(captor.capture());

        Assertions.assertEquals(entity, captor.getValue());

        Mockito.verifyNoMoreInteractions(findEmployeeService,employeeRepository);
    }

    @Test
    void verifySurname() {
        UpdateNotAllowedException expected = new UpdateNotAllowedException();
        UpdateEmployeeRequest request = UpdateEmployeeRequest.builder().surname("a").name("").build();

        UpdateNotAllowedException exception = assertThrows(UpdateNotAllowedException.class, () ->
                updateEmployeeService.verify(request));

        Assertions.assertEquals(expected.getMessage(), exception.getMessage());
    }

    @Test
    void verifyName() {
        UpdateNotAllowedException expected = new UpdateNotAllowedException();
        UpdateEmployeeRequest request = UpdateEmployeeRequest.builder().name("a").surname("").build();

        UpdateNotAllowedException exception = assertThrows(UpdateNotAllowedException.class, () ->
                updateEmployeeService.verify(request));

        Assertions.assertEquals(expected.getMessage(), exception.getMessage());
    }
}