package com.karatay.fitdb.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    INSTRUCTOR,
    CLIENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
