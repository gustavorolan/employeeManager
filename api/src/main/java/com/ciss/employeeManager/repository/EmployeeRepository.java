package com.ciss.employeeManager.repository;


import com.ciss.employeeManager.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    @Query("select emp from employee emp where emp.userAccountEntity.id = :userId")
    List<EmployeeEntity> findByUserId(
            @RequestParam("userId") Long userId
    );

    @Query("select emp from employee emp where emp.userAccountEntity.id = :userId and emp.id = :employeeId")
    Optional<EmployeeEntity> findByUserIdAndEmployeeId(
            @RequestParam("userId") Long userId,
            @RequestParam("employeeId") Long employeeId
    );
}