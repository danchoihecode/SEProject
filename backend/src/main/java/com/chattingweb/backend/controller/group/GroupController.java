package com.chattingweb.backend.controller.group;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chattingweb.backend.entities.conversation.Group;
import com.chattingweb.backend.services.group.CreateGroupRequest;
import com.chattingweb.backend.services.group.GroupService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Authentication")
@RequestMapping("/")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/groups")
    public ResponseEntity<Group> createGroup(@RequestParam(name = "groupAvatar", required = false) MultipartFile groupAvatar, 
    		@RequestBody CreateGroupRequest createGroupRequest) {
    	Group createdGroup = groupService.createGroup(groupAvatar, createGroupRequest);
        return ResponseEntity.ok(createdGroup);
    }
}