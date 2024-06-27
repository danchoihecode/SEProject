package com.chattingweb.backend.controller.user;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.services.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Authentication")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") UUID userId) {
        User user =  userService.findUserById(userId);
        return ResponseEntity.ok(user);
    }

    // Leave conversation
    @PostMapping("/{userId}/conversations/{conversationId}/leave")
    public ResponseEntity<String> leaveConversation(
            @PathVariable UUID userId,
            @PathVariable UUID conversationId) {
        userService.leaveConversation(userId, conversationId);
        return ResponseEntity.ok("User successfully left the conversation.");
    }

    // Leave group
    @PostMapping("/{userId}/groups/{groupId}/leave")
    public ResponseEntity<String> leaveGroup(
            @PathVariable UUID userId,
            @PathVariable UUID groupId,
            @RequestParam(name = "newOwnerId", required = false) UUID newOwnerId) {
        userService.leaveGroup(userId, groupId, newOwnerId);
        return ResponseEntity.ok("User successfully left the group.");
    }
    
    // Update profile
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserProfile(@PathVariable UUID userId,
            @RequestParam(name = "fullName") String fullName,
            @RequestParam(name = "nickName") String nickName,
            @RequestParam(name = "avatar", required = false) MultipartFile avatar) throws IOException {
    	User updatedUser =  userService.updateUserProfile(userId, fullName, nickName, avatar);
    	return ResponseEntity.ok(updatedUser);
	}
    
    // Get friends list
    @GetMapping("/{userId}/friends")
    public ResponseEntity<List<User>> getFriendsList(@PathVariable UUID userId) {
        List<User> friendsList = userService.getFriendsList(userId);
        return ResponseEntity.ok(friendsList);
    }
}
