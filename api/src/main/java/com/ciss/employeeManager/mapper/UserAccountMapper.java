package com.ciss.employeeManager.mapper;

import com.ciss.employeeManager.dto.request.NewUserRequest;
import com.ciss.employeeManager.model.UserAccountEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserAccountMapper {
    public UserAccountEntity toEntity(NewUserRequest request) {
        return UserAccountEntity.builder()
                .password(request.getPassword())
                .email(request.getEmail())
                .permissionEntityList(new ArrayList<>())
                .employeeList(new ArrayList<>())
                .build();
    }
}
