package com.chattingweb.backend.services;

import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.models.FriendData;
import com.chattingweb.backend.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SearchService {

    final
    UserRepository userRepository;

    public SearchService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public FriendData searchFriendsByEmail(String email){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            return new FriendData()
                    .setUuid(user.getId())
                    .setAvatar(user.getAvatar())
                    .setNickName(user.getNickName());
        }
        return null;
    }

    public List<FriendData> searchFriendsByNickName(String nickName){
        List<FriendData> friends = new ArrayList<>();
        List<User> userList = userRepository.findALlByNickNameContainingIgnoreCase(nickName, PageRequest.of(0,10));
        for(User user : userList){
            friends.add(new FriendData()
                    .setUuid(user.getId())
                    .setNickName(user.getNickName())
                    .setAvatar(user.getAvatar()));
        }
        return friends;
    }
}
