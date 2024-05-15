package com.chattingweb.backend.repository.user;

import com.chattingweb.backend.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}