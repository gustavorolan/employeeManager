package com.ciss.employeeManager.service.employee.impl;

import com.ciss.employeeManager.dto.request.NewEmployeeRequest;
import com.ciss.employeeManager.exception.EmployeeAlreadyExistsException;
import com.ciss.employeeManager.model.EmployeeEntity;
import com.ciss.employeeManager.model.UserAccountEntity;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeAlreadyExistisVerifierImplTest {

    @InjectMocks
    private  EmployeeAlreadyExistisVerifierImpl employeeAlreadyExistisVerifier;

    @Mock
    private FindUserAuthenticatedService findUserAuthenticatedService;

    @Test
    void verify() {
        EmployeeAlreadyExistsException expected = new EmployeeAlreadyExistsException();
        NewEmployeeRequest newEmployeeRequest = EmployeeFactory.getNewEmployeeRequest();
        EmployeeEntity employeeEntity = EmployeeFactory.getEntity();
        UserAccountEntity userAccountEntity = UserAccountFactory.getEntity(List.of(employeeEntity));

        Mockito.when(findUserAuthenticatedService.getUser()).thenReturn(userAccountEntity);

        EmployeeAlreadyExistsException exception = assertThrows(EmployeeAlreadyExistsException.class, () ->
                employeeAlreadyExistisVerifier.verify(newEmployeeRequest));

        Mockito.verify(findUserAuthenticatedService).getUser();

        Assertions.assertEquals(expected.getMessage(), exception.getMessage());

        Mockito.verifyNoMoreInteractions(findUserAuthenticatedService);
    }

    @Test
    void verifyEmpty() {
        NewEmployeeRequest newEmployeeRequest = EmployeeFactory.getNewEmployeeRequest();
        UserAccountEntity userAccountEntity = UserAccountFactory.getEntity(List.of());

        Mockito.when(findUserAuthenticatedService.getUser()).thenReturn(userAccountEntity);

        employeeAlreadyExistisVerifier.verify(newEmployeeRequest);

        Mockito.verify(findUserAuthenticatedService).getUser();

        Mockito.verifyNoMoreInteractions(findUserAuthenticatedService);
    }
}