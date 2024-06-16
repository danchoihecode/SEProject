package com.chattingweb.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chattingweb.backend.entities.response.MemberResponse;
import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.repository.user.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<MemberResponse> findMemberByFullName(String fullName) {
        Optional<User> user = userRepository.findByFullName(fullName);
        return user.map(u -> new MemberResponse(u.getId(), u.getFullName(), u.getNickName(), u.getAvatar(), u.getEmail()));
    }
}

