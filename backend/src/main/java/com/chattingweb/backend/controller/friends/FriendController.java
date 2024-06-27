package com.chattingweb.backend.controller.friends;

import com.chattingweb.backend.entities.user.Friends;
import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.services.friend.FriendService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@SecurityRequirement(name= "Authentication")
@RequestMapping("/friends")
@Slf4j
public class FriendController {
    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping("/add_friend/{userId}/{friendId}")
    public ResponseEntity<Friends> addFriend(@PathVariable UUID userId,
                                             @PathVariable UUID friendId){
        friendService.addFriend(userId,friendId);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/accept_friend/{userId}/{friendId}")
    public ResponseEntity<Friends> acceptFriend(@PathVariable UUID userId,
                                             @PathVariable UUID friendId){
        friendService.acceptFriend(userId,friendId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/friend_request_list/{userId}")
    public ResponseEntity<List<User>> friendRequestList(@PathVariable UUID userId){
        return  ResponseEntity.ok(friendService.getFriendRequestList(userId));
    }

    //0: not added, 1: added, 2: friend
    @GetMapping("/check_friend/{userId}/{friendId}")
    public ResponseEntity<Integer> checkFriend(@PathVariable UUID userId,
                                                @PathVariable UUID friendId){
        return ResponseEntity.ok(friendService.checkFriend(userId,friendId));
    }


}