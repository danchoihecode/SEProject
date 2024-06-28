package com.chattingweb.backend.models;

import java.util.UUID;

public class FriendItem {
    private UUID id;
    private String name;

    public FriendItem(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public FriendItem setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FriendItem setName(String name) {
        this.name = name;
        return this;
    }
}
