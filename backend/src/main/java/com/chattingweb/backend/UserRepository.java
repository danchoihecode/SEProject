package com.chattingweb.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByFirstName(String firstName);
    List<User> findByAgeLessThan(Integer age);

}
