package com.chattingweb.backend.repository.user;

import com.chattingweb.backend.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
	public Optional<User> findByEmail(String email);

	public Optional<User> findByFullName(String fullName);
}