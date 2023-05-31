package com.ciss.employeeManager.service.user;

import com.ciss.employeeManager.dto.request.NewUserRequest;

public interface NewUserService {
    Long create(NewUserRequest request);
}
