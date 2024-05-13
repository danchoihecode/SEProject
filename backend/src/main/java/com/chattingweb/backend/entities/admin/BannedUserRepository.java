package com.chattingweb.backend.entities.admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BannedUserRepository extends JpaRepository<BannedUser, BannedUserId> {
}