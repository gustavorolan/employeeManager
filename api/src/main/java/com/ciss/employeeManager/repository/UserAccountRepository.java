package com.ciss.employeeManager.repository;


import com.ciss.employeeManager.model.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Long> {
    Optional<UserAccountEntity >findByEmail(String email);
}