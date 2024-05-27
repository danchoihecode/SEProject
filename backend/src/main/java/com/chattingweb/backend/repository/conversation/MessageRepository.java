package com.chattingweb.backend.repository.conversation;

import com.chattingweb.backend.entities.conversation.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
  }