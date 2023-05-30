package com.ciss.employeeManager.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "userAccount")
@Builder
public class UserAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "userPermission")
    private List<PermissionEntity> permissionEntityList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "userAccountEntity")
    private List<EmployeeEntity> employeeList = new ArrayList<>();
}
