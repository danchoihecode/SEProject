package com.chattingweb.backend.repository.user;

import com.chattingweb.backend.entities.user.User;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
   Optional<User> findByEmail(String email);
   List<User> findALlByNickNameContainingIgnoreCase(String nickName, Pageable pageable);
}