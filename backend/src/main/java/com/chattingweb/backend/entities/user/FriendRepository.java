package com.chattingweb.backend.entities.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, FriendId> {
}