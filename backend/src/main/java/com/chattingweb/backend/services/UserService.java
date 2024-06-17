package com.chattingweb.backend.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.chattingweb.backend.entities.conversation.Conversation;
import com.chattingweb.backend.entities.conversation.ConversationMemberId;
import com.chattingweb.backend.entities.conversation.Group;
import com.chattingweb.backend.repository.conversation.ConversationMemberRepository;
import com.chattingweb.backend.repository.conversation.ConversationRepository;
import com.chattingweb.backend.repository.conversation.GroupRepository;
import org.springframework.stereotype.Service;

import com.chattingweb.backend.entities.response.MemberResponse;
import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.repository.user.UserRepository;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {


    public Optional<MemberResponse> findMemberByFullName(String fullName) {
        Optional<User> user = userRepository.findByFullName(fullName);
        return user.map(u -> new MemberResponse(u.getId(), u.getFullName(), u.getNickName(), u.getAvatar(), u.getEmail()));
    }
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;
    private final GroupRepository groupRepository;
    private final ConversationMemberRepository conversationMemberRepository;

    public UserService(UserRepository userRepository,
                       ConversationRepository conversationRepository,
                       GroupRepository groupRepository,
                       ConversationMemberRepository conversationMemberRepository) {
        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
        this.groupRepository = groupRepository;
        this.conversationMemberRepository = conversationMemberRepository;
    }

    public void leaveConversation(UUID userId, UUID conversationId) {
        User user = userRepository.findById(userId).orElseThrow();
        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow();
        ConversationMemberId conversationMemberId = new ConversationMemberId();
        conversationMemberId.setUserId(userId);
        conversationMemberId.setConversationId(conversationId);
        conversationMemberRepository.deleteById(conversationMemberId);
    }

    public void leaveGroup(UUID userId, UUID groupId, UUID newOwnerId) {
        User user = userRepository.findById(userId).orElseThrow();
        Group group = groupRepository.findById(groupId).orElseThrow();
        // If user is owner then user have to set new owner for group before leaving
        if (group.getOwner().equals(user)) {
            List<User> members = conversationMemberRepository.findUsersByConversationId(groupId);
            if (!members.isEmpty()) {
                User newOwner;
                if (newOwnerId != null && members.stream().anyMatch(m -> m.getId().equals(newOwnerId))) {
                    newOwner = members.stream().filter(m -> m.getId().equals(newOwnerId)).findFirst().orElse(null);
                } else {
                    // If the new owner ID is not provided or not valid, choose the first member as the new owner
                    newOwner = members.get(0);
                }
                group.setOwner(newOwner);
                groupRepository.save(group);
            } else {
                groupRepository.deleteById(groupId);
                return;
            }
        }
        leaveConversation(userId, groupId);
    }

    public User updateUserProfile(UUID userId, String fullName, String nickName, MultipartFile avatar) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFullName(fullName);
        user.setNickName(nickName);

        if (avatar != null && !avatar.isEmpty()) {
            user.setAvatar(avatar.getBytes());
        }

        return userRepository.save(user);
    }

    public List<User> getFriendsList(UUID userId) {
        List<User> friends = userRepository.getFriendList(userId);
        return friends;
    }
}

