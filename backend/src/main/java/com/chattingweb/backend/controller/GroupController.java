package com.chattingweb.backend.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.chattingweb.backend.entities.conversation.Group;
import com.chattingweb.backend.services.group.CreateGroupRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.chattingweb.backend.entities.response.GroupResponse;
import com.chattingweb.backend.entities.response.MemberResponse;
import com.chattingweb.backend.entities.response.MessageDTO;
import com.chattingweb.backend.services.ConversationMemberService;
import com.chattingweb.backend.services.GroupService;
import com.chattingweb.backend.services.messaging.MessageService;
import com.chattingweb.backend.services.UserService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.multipart.MultipartFile;

@RestController
@SecurityRequirement(name = "Authentication")
@RequestMapping("/")
public class GroupController {
	@Autowired
	private UserService userService;
	@Autowired
	private ConversationMemberService conversationMemberService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private MessageService messageService;

	@PostMapping("/search-user")
	public ResponseEntity<MemberResponse> searchUser(@RequestBody Map<String, Object> request) {
		try {
			String fullName = request.get("fullName").toString();
			MemberResponse memberResponse = userService.findMemberByFullName(fullName);
			return ResponseEntity.ok(memberResponse);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/add-member")
	public ResponseEntity<GroupResponse> addMember(@RequestBody Map<String, Object> request) {
		try {
			String memberIdString = (String) request.get("memberId");
			String conversationIdString = (String) request.get("conversationId");

			if (memberIdString == null || conversationIdString == null) {
				return ResponseEntity.badRequest().body(new GroupResponse("MemberId and ConversationId are required"));
			}

			UUID memberId = UUID.fromString(memberIdString);
			UUID conversationId = UUID.fromString(conversationIdString);

			conversationMemberService.addMember(memberId, conversationId);
			return ResponseEntity.ok(new GroupResponse("Member added successfully"));

		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(new GroupResponse("Member already exists in the group."));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new GroupResponse("Error: " + e.getMessage()));
		}
	}

	@PostMapping("/remove-member")
	public ResponseEntity<GroupResponse> removeMember(@RequestBody Map<String, Object> request) {
		try {
			String memberIdString = (String) request.get("memberId");
			String conversationIdString = (String) request.get("conversationId");

			if (memberIdString == null || conversationIdString == null) {
				return ResponseEntity.badRequest().body(new GroupResponse("MemberId and ConversationId are required"));
			}

			UUID memberId = UUID.fromString(memberIdString);
			UUID conversationId = UUID.fromString(conversationIdString);

			conversationMemberService.removeMember(memberId, conversationId);
			return ResponseEntity.ok(new GroupResponse("Member removed successfully"));

		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(new GroupResponse("Member not found in the group"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new GroupResponse("Error: " + e.getMessage()));
		}
	}

	@PostMapping("/delete-group")
	public ResponseEntity<GroupResponse> deleteGroup(@RequestBody Map<String, Object> request) {
		String conversationIdString = (String) request.get("conversationId");

		UUID conversationId = UUID.fromString(conversationIdString);
		groupService.deleteGroup(conversationId);

		return ResponseEntity.ok(new GroupResponse("Group removed successfully"));
	}

	@PostMapping("/update-group-name")
	public ResponseEntity<GroupResponse> updateGroupName(@RequestBody Map<String, Object> request) {
		try {
			String conversationIdString = (String) request.get("conversationId");
			String newGroupName = (String) request.get("newGroupName");

			UUID conversationId = UUID.fromString(conversationIdString);
			groupService.updateGroupName(conversationId, newGroupName);

			return ResponseEntity.ok(new GroupResponse("Group name updated successfully"));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/view-members")
	public ResponseEntity<List<MemberResponse>> getAllMembers(@RequestBody Map<String, Object> request) {
		try {
			String conversationIdString = (String) request.get("conversationId");

			UUID conversationId = UUID.fromString(conversationIdString);
			List<MemberResponse> members = conversationMemberService.getAllMembers(conversationId);

			return ResponseEntity.ok(members);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/view-group")
	public ResponseEntity<List<MessageDTO>> getAllMessages(@RequestBody Map<String, Object> request) {
		try {
			String conversationIdString = (String) request.get("conversationId");
			UUID conversationId = UUID.fromString(conversationIdString);

			List<MessageDTO> messages = messageService.findAllByConversationId(conversationId);

			return ResponseEntity.ok(messages);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/owner-id")
	public ResponseEntity<String> getGroupOwnerId(@RequestBody Map<String, Object> request) {
		try {
			String conversationIdString = (String) request.get("conversationId");
			UUID conversationId = UUID.fromString(conversationIdString);
			String id = groupService.getGroupOwnerId(conversationId);
			return ResponseEntity.ok(id);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/groups")
	public ResponseEntity<Group> createGroup(
			@RequestParam(name = "groupAvatar", required = false) MultipartFile groupAvatar,
			@RequestBody CreateGroupRequest createGroupRequest) {
		Group createdGroup = groupService.createGroup(groupAvatar, createGroupRequest);
		return ResponseEntity.ok(createdGroup);
	}
}
