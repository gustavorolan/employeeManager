package com.ciss.employeeManager.service.impl;

import com.ciss.employeeManager.dto.request.NewUserRequest;
import com.ciss.employeeManager.exception.UserAlreadyExistsException;
import com.ciss.employeeManager.model.UserAccountEntity;
import com.ciss.employeeManager.repository.UserAccountRepository;
import com.ciss.employeeManager.service.NewUserVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAlreadyExistsVerifierImpl implements NewUserVerifier {
    private final UserAccountRepository userAccountRepository;
    @Override
    public void verify(NewUserRequest request) {
        Optional<UserAccountEntity> userAccount = userAccountRepository.findByEmail(request.getEmail());
        if (userAccount.isPresent()) throw new UserAlreadyExistsException();
    }
}
