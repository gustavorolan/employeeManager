package com.ciss.employeeManager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "employee")
@Builder
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String surname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Long pisNumber;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId")
    private UserAccountEntity userAccountEntity;
}
