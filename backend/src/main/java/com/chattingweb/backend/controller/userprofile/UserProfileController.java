package com.chattingweb.backend.controller.userprofile;

import com.chattingweb.backend.entities.post.Post;
import com.chattingweb.backend.entities.response.ProfileDTO;
import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.models.UserProfile;
import com.chattingweb.backend.repository.post.PostRepository;
import com.chattingweb.backend.repository.user.UserRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@SecurityRequirement(name = "Authentication")
@RequestMapping("/")
public class UserProfileController {
	final UserRepository userRepository;
	private final PostRepository postRepository;

	public UserProfileController(UserRepository userRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	@GetMapping("/userprofile")
	public ResponseEntity<ProfileDTO> userProfile(@RequestParam(name = "id") UUID id) {
		System.out.println(id.toString());
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			try {
				List<Post> posts = postRepository.findByUser(user.get());
				return ResponseEntity.ok(new ProfileDTO(user.get().getId(), user.get().getNickName(),
						user.get().getFullName(), user.get().getAvatar(), posts));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.notFound().build();
			}

		}
		return ResponseEntity.notFound().build();
	}

}
