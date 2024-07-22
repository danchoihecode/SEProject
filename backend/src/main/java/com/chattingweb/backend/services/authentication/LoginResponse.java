package com.chattingweb.backend.services.authentication;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class LoginResponse {
    @Setter
    private String token;

    @Setter
    private long expiresIn;

    private UUID userId;
    
    @Setter
    private boolean admin;

    public LoginResponse setUserId(UUID userId) {
        this.userId = userId;
        return this;
    }

}
