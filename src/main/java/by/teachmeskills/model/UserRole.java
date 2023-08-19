package by.teachmeskills.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_EMPLOYER,
    ROLE_CANDIDATE,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
