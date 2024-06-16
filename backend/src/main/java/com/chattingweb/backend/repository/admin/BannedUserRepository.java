package com.chattingweb.backend.repository.admin;

import com.chattingweb.backend.entities.admin.BannedUser;
import com.chattingweb.backend.entities.admin.BannedUserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannedUserRepository extends JpaRepository<BannedUser, BannedUserId> {
  }