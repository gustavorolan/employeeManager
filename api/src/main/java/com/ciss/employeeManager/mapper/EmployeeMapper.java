package com.ciss.employeeManager.mapper;

import com.ciss.employeeManager.dto.request.NewEmployeeRequest;
import com.ciss.employeeManager.dto.response.EmployeeResponse;
import com.ciss.employeeManager.model.EmployeeEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeMapper {
    public EmployeeEntity toEntity(NewEmployeeRequest request) {
        return EmployeeEntity.builder()
                .name(request.getName())
                .pisNumber(request.getPisNumber())
                .email(request.getEmail())
                .surname(request.getSurname())
                .build();
    }

    public EmployeeResponse toResponse(EmployeeEntity employeeEntity) {
        return EmployeeResponse.builder()
                .surname(employeeEntity.getSurname())
                .email(employeeEntity.getEmail())
                .pisNumber(employeeEntity.getPisNumber())
                .name(employeeEntity.getName())
                .build();
    }

    public List<EmployeeResponse> toResponse(List<EmployeeEntity> employeeEntityList) {
        return employeeEntityList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
