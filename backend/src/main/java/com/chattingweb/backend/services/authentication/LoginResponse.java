package com.chattingweb.backend.services.authentication;

import java.util.UUID;

public class LoginResponse {
    private String token;

    private long expiresIn;

    private UUID userId;

    public UUID getUserId() {
        return userId;
    }

    public LoginResponse setUserId(UUID userId) {
        this.userId = userId;
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
