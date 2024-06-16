package com.chattingweb.backend.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chattingweb.backend.entities.conversation.Conversation;
import com.chattingweb.backend.entities.conversation.ConversationMember;
import com.chattingweb.backend.entities.conversation.ConversationMemberId;
import com.chattingweb.backend.entities.response.MemberResponse;
import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.repository.conversation.ConversationMemberRepository;
import com.chattingweb.backend.repository.conversation.ConversationRepository;
import com.chattingweb.backend.repository.user.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ConversationMemberService {
	@Autowired
	private ConversationMemberRepository conversationMemberRepository;

	@Autowired
	private ConversationRepository conversationRepository;

	@Autowired
	private UserRepository userRepository;

	public void addMember(UUID memberId, UUID conversationId) {
		Conversation conversation = conversationRepository.findById(conversationId)
				.orElseThrow(() -> new EntityNotFoundException("Conversation not found with id: " + conversationId));

		User member = userRepository.findById(memberId)
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + memberId));

		ConversationMemberId key = new ConversationMemberId(memberId, conversationId);
		if (conversationMemberRepository.existsById(key)) {
			throw new IllegalArgumentException();
		}

		ConversationMember conversationMember = new ConversationMember(key, member, conversation, false);
		conversationMemberRepository.save(conversationMember);
	}

	public void removeMember(UUID memberId, UUID conversationId) {
		ConversationMemberId key = new ConversationMemberId(memberId, conversationId);
		if (!conversationMemberRepository.existsById(key)) {
			throw new EntityNotFoundException();
		}

		conversationMemberRepository.deleteById(key);
	}
	public List<MemberResponse> getAllMembers(UUID conversationId) {
        List<User> users = conversationMemberRepository.findAllByConversationId(conversationId);
        return users.stream()
                .map(user -> new MemberResponse(user.getId(), user.getFullName(), user.getNickName(), user.getAvatar(), user.getEmail()))
                .collect(Collectors.toList());
    }
}
