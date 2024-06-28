package com.chattingweb.backend.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.chattingweb.backend.entities.conversation.Conversation;
import com.chattingweb.backend.entities.conversation.ConversationMember;
import com.chattingweb.backend.entities.conversation.ConversationMemberId;
import com.chattingweb.backend.entities.conversation.Group;
import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.repository.conversation.ConversationMemberRepository;
import com.chattingweb.backend.repository.conversation.ConversationRepository;
import com.chattingweb.backend.repository.conversation.GroupRepository;
import com.chattingweb.backend.repository.conversation.MessageRepository;
import com.chattingweb.backend.repository.user.UserRepository;
import com.chattingweb.backend.services.group.CreateGroupRequest;
@Transactional

@Service
public class GroupService {

	private final ConversationRepository conversationRepository;
	private final GroupRepository groupRepository;
	@Autowired
	private MessageRepository messageRepository;

	public void deleteGroup(UUID conversationId) {
		messageRepository.deleteByConversationId(conversationId);
		conversationMemberRepository.deleteByConversationId(conversationId);
		groupRepository.deleteById(conversationId);
		conversationRepository.deleteById(conversationId);
		System.out.println("Delete success");
	}
	

	public void updateGroupName(UUID conversationId, String newGroupName) {
		Optional<Group> group = groupRepository.findById(conversationId);
		if (group.isPresent()) {
			Group gr = group.get();
			if (gr.getGroupName().equals(newGroupName))
			throw new IllegalArgumentException("Group name is the same as before");
			gr.setGroupName(newGroupName);
			groupRepository.save(gr);
		} else {
			throw new IllegalArgumentException("Group not found for conversation ID: " + conversationId);
		}
	}
	
	public String getGroupOwnerId(UUID conversationId) {
        Optional<Group> gr = groupRepository.findById(conversationId);
        if (gr.isPresent()) {
        	Group group = gr.get();
            User owner = group.getOwner();
            if (owner != null) {
                return owner.getId().toString(); 
            } else {
                throw new IllegalArgumentException("Owner not found for the group");
            }
        } else {
            throw new IllegalArgumentException("Group not found for the given conversation ID");
        }
    }
	
	

	private final UserRepository userRepository;
	private final ConversationMemberRepository conversationMemberRepository;

	public GroupService(GroupRepository groupRepository, UserRepository userRepository,
						ConversationRepository conversationRepository,
						ConversationMemberRepository conversationMemberRepository) {
		this.userRepository = userRepository;
		this.conversationMemberRepository = conversationMemberRepository;
		this.conversationRepository = conversationRepository;
		this.groupRepository = groupRepository;
	}

	public Group createGroup( CreateGroupRequest createGroupRequest) {
		Group group = new Group();

		//set group name
		group.setGroupName(createGroupRequest.getGroupName());

		//set owner
		User owner = userRepository.findById(createGroupRequest.getOwnerId()).orElse(null);
		if (owner == null) {
			throw new IllegalArgumentException("User not found");
		}
		group.setOwner(owner);


		//create new conversation
		Conversation conversation = new Conversation();
		conversation.setIsGroup(true);
		conversationRepository.save(conversation);
		group.setConversation(conversation);
		group.setId(conversation.getId());

		//set members of group
		List<UUID> members = createGroupRequest.getSelectedFriendIds();
		members.add(owner.getId());
		for (UUID memberId : members) {
			ConversationMember conversationMember = new ConversationMember();
			ConversationMemberId conversationMemberId = new ConversationMemberId();
			conversationMemberId.setConversationId(group.getId());
			conversationMemberId.setUserId(memberId);
			conversationMember.setId(conversationMemberId);
			conversationMember.setConversation(conversation);
			User member = userRepository.findById(memberId).orElse(null);
			conversationMember.setUser(member);
			conversationMemberRepository.save(conversationMember);
		}

		return groupRepository.save(group);
	}
}
