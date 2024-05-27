package com.chattingweb.backend.controller.friends;

import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.modules.FriendData;
import com.chattingweb.backend.repository.user.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/friends")
public class SearchFriendController {

    final
    UserRepository userRepository;

    public SearchFriendController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<FriendData> searchFriendsByEmail(
            @Email(message = "Email Format Invalid!")
            @RequestParam(name = "email") String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            FriendData friend = new FriendData()
                    .setUuid(user.getId())
                    .setAvatar(user.getAvatar())
                    .setNickName(user.getNickName());
            return ResponseEntity.ok(friend);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<FriendData>> searchFriendsByNickName(
            @Valid @RequestParam(name = "nickName") String nickName){
        List<FriendData> friends = new ArrayList<>();

        List<User> userList = userRepository.findALlByNickNameContainingIgnoreCase(nickName, PageRequest.of(0,10));
        for(User user : userList){
            friends.add(new FriendData()
                    .setUuid(user.getId())
                    .setNickName(user.getNickName())
                    .setAvatar(user.getAvatar()));
        }
        if(friends.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(friends);
    }
}
