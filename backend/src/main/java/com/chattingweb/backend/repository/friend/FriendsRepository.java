package com.chattingweb.backend.repository.friend;

import com.chattingweb.backend.entities.user.FriendId;
import com.chattingweb.backend.entities.user.Friends;
import com.chattingweb.backend.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FriendsRepository extends JpaRepository<Friends, FriendId> {
    @Query("SELECT f FROM Friends as f WHERE f.user.id = :userId and f.friend.id= :friendId ")
    List<Friends> findByUserIdAndFriendId(@Param("userId") UUID userId, @Param("friendId") UUID friendId);

    @Query("SELECT f.user FROM Friends as f WHERE f.friend.id = :userId and f.accepted= false ")
    List<User> getFriendRequestList(@Param("userId") UUID userId);
}