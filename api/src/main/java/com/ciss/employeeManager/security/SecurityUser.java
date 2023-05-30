package com.ciss.employeeManager.security;

import com.ciss.employeeManager.model.UserAccountEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

public class SecurityUser implements UserDetails {
    private final Long id;
    private final String password;
    private final String email;
    private final List<SimpleGrantedAuthority> permissions;

    public SecurityUser (UserAccountEntity user){
        this.id= user.getId();
        this.email=user.getEmail();
        this.password= user.getPassword();
        this.permissions = user.getPermissionEntityList()
                .stream()
                .map(permission -> new SimpleGrantedAuthority("ROLE_"+ permission.getName()))
                .collect(Collectors.toList());
    }

    public Long getId (){
        return this.id;
    }
    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
