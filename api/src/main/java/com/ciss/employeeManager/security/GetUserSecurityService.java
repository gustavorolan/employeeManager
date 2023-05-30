package com.ciss.employeeManager.security;

import com.ciss.employeeManager.model.UserAccountEntity;
import com.ciss.employeeManager.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserSecurityService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccountEntity userAccount = userAccountRepository.findByEmail(email).get();
        return new SecurityUser(userAccount);
    }
}
