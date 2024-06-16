package com.chattingweb.backend.repository.conversation;

import com.chattingweb.backend.entities.conversation.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConversationRepository extends JpaRepository<Conversation, UUID> {
  }