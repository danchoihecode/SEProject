package com.chattingweb.backend.repository.user;

import com.chattingweb.backend.entities.user.Friend;
import com.chattingweb.backend.entities.user.FriendId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, FriendId> {
}