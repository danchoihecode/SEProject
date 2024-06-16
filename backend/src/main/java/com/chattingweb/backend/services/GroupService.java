package com.chattingweb.backend.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chattingweb.backend.entities.conversation.Group;
import com.chattingweb.backend.repository.conversation.ConversationRepository;
import com.chattingweb.backend.repository.conversation.GroupRepository;

@Service
public class GroupService {

	@Autowired
	private ConversationRepository conversationRepository;
	@Autowired
    private GroupRepository groupRepository;

	public void deleteGroup(UUID conversationId) {
		conversationRepository.deleteById(conversationId);
	}
	

	public void updateGroupName(UUID conversationId, String newGroupName) {
		Optional<Group> group = groupRepository.findById(conversationId);
		if (group.isPresent()) {
			Group gr = group.get();
			gr.setGroupName(newGroupName);
			groupRepository.save(gr);
		} else {
			throw new IllegalArgumentException("Group not found for conversation ID: " + conversationId);
		}
	}
}
