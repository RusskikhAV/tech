package ru.inside.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_CLIENT,
    ROLE_EMPLOYEE,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
