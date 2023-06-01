package com.ciss.employeeManager.service.employee.impl;

import com.ciss.employeeManager.model.EmployeeEntity;
import com.ciss.employeeManager.repository.EmployeeRepository;
import com.ciss.employeeManager.service.employee.FindEmployeeService;
import com.ciss.employeeManager.utils.factory.EmployeeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteEmployeeServiceImplTest {
    @InjectMocks
    private DeleteEmployeeServiceImpl deleteEmployeeService;

    @Mock
    private FindEmployeeService findEmployeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Captor
    private ArgumentCaptor<EmployeeEntity> captor;

    @Test
    void delete() {
        EmployeeEntity entity = EmployeeFactory.getEntity();

        Mockito.when(findEmployeeService.getEntityById(entity.getId())).thenReturn(entity);

        deleteEmployeeService.delete(entity.getId());

        Mockito.verify(findEmployeeService).getEntityById(entity.getId());
        Mockito.verify(employeeRepository).delete(captor.capture());

        Assertions.assertEquals(entity, captor.getValue());

        Mockito.verifyNoMoreInteractions(findEmployeeService,employeeRepository);
    }
}