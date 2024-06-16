package com.chattingweb.backend.repository.conversation;

import com.chattingweb.backend.entities.conversation.ConversationMember;
import com.chattingweb.backend.entities.conversation.ConversationMemberId;
import com.chattingweb.backend.entities.user.User;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConversationMemberRepository extends 
JpaRepository<ConversationMember, ConversationMemberId> {
	@Query("SELECT cm.user FROM ConversationMember cm WHERE cm.id.conversationId = :conversationId")
    List<User> findUsersByConversationId(@Param("conversationId") UUID conversationId);

	List<User> findAllByConversationId(UUID conversationId);
  }