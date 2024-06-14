package com.chattingweb.backend.repository.conversation;

import com.chattingweb.backend.entities.conversation.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {
  }