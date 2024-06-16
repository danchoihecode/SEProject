package com.chattingweb.backend.services.group;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
import com.chattingweb.backend.repository.user.UserRepository;

@Transactional
@Service
public class GroupService {

	private final GroupRepository groupRepository;
	private final UserRepository userRepository;
	private final ConversationRepository conversationRepository;
	private final ConversationMemberRepository conversationMemberRepository;

	public GroupService(GroupRepository groupRepository, UserRepository userRepository,
			ConversationRepository conversationRepository, 
			ConversationMemberRepository conversationMemberRepository) {
		this.groupRepository = groupRepository;
		this.userRepository = userRepository;
		this.conversationRepository = conversationRepository;
		this.conversationMemberRepository = conversationMemberRepository;
	}

    public Group createGroup(MultipartFile groupAvatar, CreateGroupRequest createGroupRequest) {
    	Group group = new Group();
    	
    	//set group name
    	group.setGroupName(createGroupRequest.getGroupName());
    	
    	//set owner
    	User owner = userRepository.findById(createGroupRequest.getOwnerId()).orElse(null);
        if (owner == null) {
        	throw new IllegalArgumentException("User not found");
        }
        group.setOwner(owner);
    
		//set group avatar
        if (groupAvatar != null) {
            try {
				group.setGroupAvatar(groupAvatar.getBytes());
			} catch (IOException e) {
				System.out.println(e);
				throw new RuntimeException("Error processing group avatar", e);
			}
        }
        
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
