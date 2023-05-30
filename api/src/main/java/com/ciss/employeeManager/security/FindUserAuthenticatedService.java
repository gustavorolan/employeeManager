package com.ciss.employeeManager.security;


import com.ciss.employeeManager.model.UserAccountEntity;
import com.ciss.employeeManager.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindUserAuthenticatedService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountEntity getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        return  userAccountRepository.findById(securityUser.getId()).get();
    }
}
