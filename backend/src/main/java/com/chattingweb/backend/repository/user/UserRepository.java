package com.chattingweb.backend.repository.user;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chattingweb.backend.entities.user.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	Optional<User> findByEmail(String email);
	List<User> findALlByNickNameContainingIgnoreCase(String nickName, Pageable pageable);

   @Query(value = "Select u from User u Where u.is_Admin=TRUE",nativeQuery = true)
   public  Optional<User> findAdmin();
	
	@Query("SELECT f.friend FROM Friends f WHERE f.user.id = :userId")
    List<User> getFriendList(@Param("userId") UUID userId);
}