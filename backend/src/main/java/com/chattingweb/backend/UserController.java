package com.chattingweb.backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable Integer id){
        return userRepository.findById(id);
    }

    @GetMapping
    public List<User> findByAgeLessThan(@RequestParam(name = "age",required = false) Integer age){
        return userRepository.findByAgeLessThan(age);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createUser(@RequestBody User user){
        userRepository.save(user);
    }
}
