package com.chattingweb.backend.repository.conversation;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chattingweb.backend.entities.conversation.Group;

public interface GroupRepository extends JpaRepository<Group, UUID> {

	Optional<Group> findById(UUID conversationId);
  }