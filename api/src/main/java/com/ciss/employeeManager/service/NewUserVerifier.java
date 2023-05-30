package com.ciss.employeeManager.service;

import com.ciss.employeeManager.dto.request.NewUserRequest;

public interface  NewUserVerifier {
    void verify(NewUserRequest request);
}
