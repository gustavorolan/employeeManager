package com.ciss.employeeManager.utils.factory;

import com.ciss.employeeManager.dto.request.NewEmployeeRequest;
import com.ciss.employeeManager.dto.request.UpdateEmployeeRequest;
import com.ciss.employeeManager.dto.response.EmployeeResponse;
import com.ciss.employeeManager.model.EmployeeEntity;
import com.ciss.employeeManager.model.UserAccountEntity;

public class EmployeeFactory {

    private static final String email = "employee@email.com";
    private static final String surname = "employee surname";
    private static final String name = "employee";
    private static final Long pisNumber = 12345678910L;
    private static final Long id = 1L;

    public static EmployeeEntity getEntity(UserAccountEntity userAccountEntity) {
        return EmployeeEntity.builder()
                .id(id)
                .email(email)
                .surname(surname)
                .pisNumber(pisNumber)
                .name(name)
                .userAccountEntity(userAccountEntity)
                .build();
    }

    public static EmployeeEntity getEntity() {
        return getEntity(null);
    }

    public static EmployeeEntity getEntityWithUser() {
        return getEntity(UserAccountFactory.getEntityWithoutEmployee());
    }

    public static NewEmployeeRequest getNewEmployeeRequest(){
        return NewEmployeeRequest.builder()
                .pisNumber(pisNumber)
                .name(name)
                .email(email)
                .surname(surname)
                .build();
    }

    public static EmployeeResponse getEmployeeResponse(){
        return  EmployeeResponse.builder()
                .id(id)
                .email(email)
                .surname(surname)
                .pisNumber(pisNumber)
                .name(name)
                .build();
    }

    public static UpdateEmployeeRequest getUpdateEmployeeRequest(){
        return UpdateEmployeeRequest.builder()
                .employeeId(id)
                .email("new@email.com")
                .surname("new surname")
                .pisNumber(987456120L)
                .name("newName")
                .build();
    }

    public static EmployeeEntity getEntityUpdated(){
        return EmployeeEntity.builder()
                .id(id)
                .email("new@email.com")
                .surname("new surname")
                .pisNumber(987456120L)
                .name("newName")
                .build();
    }
}
