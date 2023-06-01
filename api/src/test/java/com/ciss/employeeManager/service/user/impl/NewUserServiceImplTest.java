package com.ciss.employeeManager.service.user.impl;

import com.ciss.employeeManager.dto.request.NewUserRequest;
import com.ciss.employeeManager.mapper.UserAccountMapper;
import com.ciss.employeeManager.model.UserAccountEntity;
import com.ciss.employeeManager.repository.UserAccountRepository;
import com.ciss.employeeManager.service.user.NewUserVerifier;
import com.ciss.employeeManager.utils.factory.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@ExtendWith(MockitoExtension.class)
class NewUserServiceImplTest {

    @InjectMocks
    private NewUserServiceImpl newUserService;

    @Mock
    private  UserAccountRepository userAccountRepository;

    @Mock
    private  PasswordEncoder passwordEncoder;

    @Mock
    private  UserAccountMapper userAccountMapper;

    @Mock
    private  List<NewUserVerifier> newUserVerifierList;

    @Captor
    private ArgumentCaptor<UserAccountEntity> captor;

    @Test
    void create() {
        NewUserRequest newUserRequest = UserAccountFactory.getNewUserRequest();
        UserAccountEntity entityWithoutEmployee = UserAccountFactory.getEntityWithoutEmployee();

        Mockito.when(passwordEncoder.encode(newUserRequest.getPassword())).thenReturn(newUserRequest.getPassword());
        Mockito.when(userAccountMapper.toEntity(newUserRequest)).thenReturn(entityWithoutEmployee);
        Mockito.when(userAccountRepository.save(entityWithoutEmployee)).thenReturn(entityWithoutEmployee);

        Long result = newUserService.create(newUserRequest);

        Mockito.verify(passwordEncoder).encode(newUserRequest.getPassword());
        Mockito.verify(userAccountMapper).toEntity(newUserRequest);
        Mockito.verify(userAccountRepository).save(captor.capture());

        Assertions.assertEquals(result, entityWithoutEmployee.getId());
        Assertions.assertEquals(entityWithoutEmployee, captor.getValue());
    }
}