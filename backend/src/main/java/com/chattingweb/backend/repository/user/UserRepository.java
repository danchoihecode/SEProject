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
	
	@Query("SELECT f.friend FROM Friends as f WHERE f.user.id = :userId and f.accepted= true ")
    List<User> getFriendList(@Param("userId") UUID userId);
	
	Optional<User> findByFullName(String fullName);
}