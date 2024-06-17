package com.chattingweb.backend.controller.auth;

import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.services.JwtService;
import com.chattingweb.backend.services.authentication.AuthenticationService;
import com.chattingweb.backend.services.authentication.LoginResponse;
import com.chattingweb.backend.services.authentication.LoginUser;
import com.chattingweb.backend.services.authentication.RegisterUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody RegisterUser registerUser) {
        User registeredUser = authenticationService.signup(registerUser);
        log.info("Registering user {}", registerUser.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginUser loginUser) {
        User authenticatedUser = authenticationService.authenticate(loginUser);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse().setUserId(authenticatedUser.getId());
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        log.info("Authenticated user {}", authenticatedUser.getEmail());
        return ResponseEntity.ok(loginResponse);
    }
}
