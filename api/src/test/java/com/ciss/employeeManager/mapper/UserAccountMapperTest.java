package com.ciss.employeeManager.mapper;

import com.ciss.employeeManager.dto.request.NewUserRequest;
import com.ciss.employeeManager.model.UserAccountEntity;
import com.ciss.employeeManager.utils.factory.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserAccountMapperTest {

    @InjectMocks
    private UserAccountMapper userAccountMapper;

    @Test
    void toEntity() {
        UserAccountEntity expected = UserAccountFactory.getEntityWithoutEmployee();
        expected.setId(null);
        NewUserRequest newUserRequest = UserAccountFactory.getNewUserRequest();
        UserAccountEntity result = userAccountMapper.toEntity(newUserRequest);
        Assertions.assertEquals(expected,result);
    }
}