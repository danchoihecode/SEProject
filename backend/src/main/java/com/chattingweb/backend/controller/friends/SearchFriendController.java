package com.chattingweb.backend.controller.friends;

import com.chattingweb.backend.entities.user.User;
import com.chattingweb.backend.models.FriendData;
import com.chattingweb.backend.repository.user.UserRepository;
import com.chattingweb.backend.services.SearchService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@SecurityRequirement(name = "Authentication")
@RequestMapping("/friends")
@Slf4j
public class SearchFriendController {

    private final SearchService searchService;

    public SearchFriendController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/by-email")
    public ResponseEntity<FriendData> searchFriendsByEmail(
            @Valid
            @Email(message = "Email Format Invalid!")
            @RequestParam(name = "email") String email) {
        FriendData friendData = searchService.searchFriendsByEmail(email);
        if(friendData != null){
            log.info("Find friend by email: {}", email);
            return ResponseEntity.ok(friendData);
        }
        log.info("Not found user by email: {}", email);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/by-nick-name")
    public ResponseEntity<List<FriendData>> searchFriendsByNickName(
            @Valid @RequestParam(name = "nick-name") String nickName){
        List<FriendData> friendDataList = searchService.searchFriendsByNickName(nickName);
        if(friendDataList.isEmpty()){
            log.info("Not found friends by name: {}", nickName);
            return ResponseEntity.notFound().build();
        }
        log.info("Found friends by name: {}", nickName);
        return ResponseEntity.ok(friendDataList);
    }
}
