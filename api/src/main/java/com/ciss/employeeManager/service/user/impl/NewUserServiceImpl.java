package com.ciss.employeeManager.service.user.impl;

import com.ciss.employeeManager.dto.request.NewUserRequest;
import com.ciss.employeeManager.mapper.UserAccountMapper;
import com.ciss.employeeManager.model.UserAccountEntity;
import com.ciss.employeeManager.repository.UserAccountRepository;
import com.ciss.employeeManager.service.user.NewUserService;
import com.ciss.employeeManager.service.user.NewUserVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NewUserServiceImpl implements NewUserService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAccountMapper userAccountMapper;
    private final List<NewUserVerifier> newUserVerifierList;

    @Override
    public Long create(NewUserRequest request) {

        verify(request);

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        UserAccountEntity entity = userAccountMapper.toEntity(request);

        UserAccountEntity save = userAccountRepository.save(entity);

        return save.getId();
    }

    private void verify(NewUserRequest request) {
        newUserVerifierList.forEach(newUserVerifier -> newUserVerifier.verify(request));
    }
}
