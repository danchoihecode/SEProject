package com.chattingweb.backend;

import jakarta.persistence.*;

@Entity
@Table(name="USER")
public class User {
    @Id
    @GeneratedValue
    private Integer userId;
    @Column
    private String firstName;
    @Column
    private String lastName;

    @Column
    private Integer age;

    public User(Integer id, String firstName, String lastName, Integer age){
        this.firstName =firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
