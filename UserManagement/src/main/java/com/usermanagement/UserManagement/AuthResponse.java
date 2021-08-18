package com.usermanagement.UserManagement;

public class AuthResponse {

    private final String jwt;

    public String getJwt() {
        return jwt;
    }

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

}
