package com.chattingweb.backend.services.friend;

import com.chattingweb.backend.entities.conversation.Conversation;
import com.chattingweb.backend.entities.conversation.ConversationMember;
import com.chattingweb.backend.entities.conversation.ConversationMemberId;
import com.chattingweb.backend.entities.user.FriendId;
import com.chattingweb.backend.entities.user.Friends;
import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.repository.conversation.ConversationMemberRepository;
import com.chattingweb.backend.repository.conversation.ConversationRepository;
import com.chattingweb.backend.repository.friend.FriendsRepository;
import com.chattingweb.backend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FriendService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversationMemberRepository conversationMemberRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private FriendsRepository friendsRepository;

    public void addFriend(UUID userId, UUID friendId){
        User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        User friend=userRepository.findById(friendId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Friends> friendRequest= friendsRepository.findByUserIdAndFriendId(userId,friendId);
        List<Friends> friendRequest2= friendsRepository.findByUserIdAndFriendId(friendId,userId);

        if(friendRequest.isEmpty()&&friendRequest2.isEmpty()){
            Friends friends=new Friends();
            friends.setFriend(friend);
            friends.setUser(user);
            FriendId friendId1=new FriendId();
            friendId1.setFriendId(friendId);
            friendId1.setUserId(userId);
            friends.setId(friendId1);
            friends.setAccepted(false);
            friendsRepository.save(friends);;
        }
    }

    public void acceptFriend(UUID userId,UUID friendId){
        User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        User friend=userRepository.findById(friendId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Friends> friendRequest= friendsRepository.findByUserIdAndFriendId(friendId,userId);

        Conversation conversation = new Conversation();
        conversation.setIsGroup(false);

        Conversation newConversation = conversationRepository.save(conversation);
        ConversationMemberId id1=new ConversationMemberId(newConversation.getId(),userId);
        ConversationMemberId id2=new ConversationMemberId(newConversation.getId(),friendId);
        ConversationMember conversationMember1 = new ConversationMember(id1,user,newConversation,true);
        conversationMemberRepository.save(conversationMember1);
        ConversationMember conversationMember2 = new ConversationMember(id2,friend,newConversation,true);
        conversationMemberRepository.save(conversationMember2);

        if(!friendRequest.isEmpty()){
            Friends friends=friendRequest.get(0);
            FriendId friendsId=new FriendId();
            friendsId.setUserId(userId);
            friendsId.setFriendId(friendId);
            Friends friends1= new Friends();
            friends1.setId(friendsId);
            friends1.setUser(user);
            friends1.setFriend(friend);
            friends1.setAccepted(true);
            friends.setAccepted(true);
            friendsRepository.save(friends);
            friendsRepository.save(friends1);
        }
    }

    public List<User> getFriendRequestList(UUID userId){
        User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return friendsRepository.getFriendRequestList(userId);
    }

    public Integer checkFriend(UUID userId, UUID friendId){
        User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        User friend=userRepository.findById(friendId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Friends> friendRequest= friendsRepository.findByUserIdAndFriendId(userId,friendId);
        if(!friendRequest.isEmpty()){
            boolean status=friendRequest.get(0).isAccepted();
            if(status){
                return 2;
            }
            else return 1;
        }
        else return 0;
    }
}
