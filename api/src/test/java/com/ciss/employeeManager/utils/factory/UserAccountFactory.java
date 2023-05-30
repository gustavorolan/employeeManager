package com.ciss.employeeManager.utils.factory;

import com.ciss.employeeManager.dto.request.NewUserRequest;
import com.ciss.employeeManager.model.EmployeeEntity;
import com.ciss.employeeManager.model.UserAccountEntity;

import java.util.List;

public class UserAccountFactory {
    private final  static String email = "user@email.com";
    private final  static String password = "12345678";
    private final  static Long id = 1L;
    public static UserAccountEntity getEntity(List<EmployeeEntity> employeeEntityList){
        return UserAccountEntity.builder()
                .id(id)
                .email(email)
                .password(password)
                .employeeList(employeeEntityList)
                .permissionEntityList(List.of())
                .build();
    }

    public static UserAccountEntity getEntityWithoutEmployee() {
        return getEntity(List.of());
    }

    public static NewUserRequest getNewUserRequest(){
        return NewUserRequest.builder()
                .email(email)
                .password(password)
                .build();
    }
}
