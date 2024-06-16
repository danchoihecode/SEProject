package com.chattingweb.backend.repository.conversation;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chattingweb.backend.entities.conversation.ConversationMember;
import com.chattingweb.backend.entities.conversation.ConversationMemberId;
import com.chattingweb.backend.entities.user.User;

public interface ConversationMemberRepository extends JpaRepository<ConversationMember, ConversationMemberId> {

	@Query("SELECT cm.user FROM ConversationMember cm WHERE cm.conversation.id = :conversationId")
	List<User> findAllByConversationId(UUID conversationId);
}