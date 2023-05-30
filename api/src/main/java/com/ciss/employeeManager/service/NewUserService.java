package com.ciss.employeeManager.service;

import com.ciss.employeeManager.dto.request.NewUserRequest;

public interface NewUserService {
    Long create(NewUserRequest request);
}
