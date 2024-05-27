package com.chattingweb.backend.services.authentication;

import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.repository.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User signup(RegisterUser registerUser){
        User user = new User();
        user.setFullName(registerUser.getFullName());
        user.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        user.setEmail(registerUser.getEmail());
        int newId = userRepository.findAll().size() + 1;
//        user.setId(newId);
        user.setNickName("user" + newId);
        return userRepository.save(user);
    }

    public User authenticate(LoginUser loginUser) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getEmail(),
                        loginUser.getPassword()
                )
        );
        return userRepository.findByEmail(loginUser.getEmail()).orElseThrow();
    }

}
