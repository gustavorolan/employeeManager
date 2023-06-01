package com.ciss.employeeManager.service.user.impl;

import com.ciss.employeeManager.dto.request.NewUserRequest;
import com.ciss.employeeManager.exception.UserAlreadyExistsException;
import com.ciss.employeeManager.model.UserAccountEntity;
import com.ciss.employeeManager.repository.UserAccountRepository;
import com.ciss.employeeManager.utils.factory.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UserAlreadyExistsVerifierImplTest {

    @InjectMocks
    private UserAlreadyExistsVerifierImpl userAlreadyExistsVerifier;

    @Mock
    private UserAccountRepository userAccountRepository;

    @Test
    void verify() {
        UserAlreadyExistsException expected = new UserAlreadyExistsException();
        UserAccountEntity userAccountEntity = UserAccountFactory.getEntityWithoutEmployee();
        NewUserRequest newUserRequest = UserAccountFactory.getNewUserRequest();

        Mockito.when(userAccountRepository.findByEmail(newUserRequest.getEmail()))
                .thenReturn(Optional.of(userAccountEntity));

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () ->
                userAlreadyExistsVerifier.verify(newUserRequest));

        Mockito.verify(userAccountRepository).findByEmail(newUserRequest.getEmail());

        Assertions.assertEquals(expected.getMessage(), exception.getMessage());
    }

    @Test
    void verifyEmpty() {
        NewUserRequest newUserRequest = UserAccountFactory.getNewUserRequest();

        Mockito.when(userAccountRepository.findByEmail(newUserRequest.getEmail()))
                .thenReturn(Optional.empty());

        userAlreadyExistsVerifier.verify(newUserRequest);

        Mockito.verify(userAccountRepository).findByEmail(newUserRequest.getEmail());
    }
}