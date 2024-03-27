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

}
