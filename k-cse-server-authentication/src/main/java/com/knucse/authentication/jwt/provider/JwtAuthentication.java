package com.knucse.authentication.jwt.provider;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.knucse.authentication.security.details.PrincipalDetails;

public class JwtAuthentication implements Authentication {
    private final String email;
    private final String accessToken;

    private boolean isAuthenticated = true;

    private JwtAuthentication(String email, String accessToken) {
        this.email = email;
        this.accessToken = accessToken;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getCredentials() {
        return accessToken;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return email;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return email;
    }

    public static JwtAuthentication of(String email, String accessToken) {
        return new JwtAuthentication(email, accessToken);
    }
}
