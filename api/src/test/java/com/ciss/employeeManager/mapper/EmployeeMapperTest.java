package com.ciss.employeeManager.mapper;

import com.ciss.employeeManager.dto.request.NewEmployeeRequest;
import com.ciss.employeeManager.dto.response.EmployeeResponse;
import com.ciss.employeeManager.model.EmployeeEntity;
import com.ciss.employeeManager.model.UserAccountEntity;
import com.ciss.employeeManager.utils.factory.EmployeeFactory;
import com.ciss.employeeManager.utils.factory.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class EmployeeMapperTest {

    @InjectMocks
    private EmployeeMapper employeeMapper;

    @Test
    void toEntity() {
        UserAccountEntity userAccountEntity = UserAccountFactory.getEntityWithoutEmployee();
        EmployeeEntity expected = EmployeeFactory.getEntity(userAccountEntity);
        expected.setId(null);
        NewEmployeeRequest newEmployeeRequest = EmployeeFactory.getNewEmployeeRequest();
        EmployeeEntity result = employeeMapper.toEntity(newEmployeeRequest,userAccountEntity);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void toResponse() {
        EmployeeEntity entity = EmployeeFactory.getEntity();
        EmployeeResponse expected = EmployeeFactory.getEmployeeResponse();
        EmployeeResponse result = employeeMapper.toResponse(entity);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void listToResponse() {
        List<EmployeeEntity> entityList = List.of(EmployeeFactory.getEntity());
        List<EmployeeResponse> expected =List.of(EmployeeFactory.getEmployeeResponse());
        List<EmployeeResponse> result = employeeMapper.toResponse(entityList);
        Assertions.assertEquals(expected, result);
    }
}