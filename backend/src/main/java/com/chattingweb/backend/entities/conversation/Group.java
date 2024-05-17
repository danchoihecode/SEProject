package com.chattingweb.backend.entities.conversation;

import com.chattingweb.backend.entities.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @Column(name = "groupid", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "groupid", nullable = false)
    private Conversation conversation;

    @Column(name = "groupname", nullable = false, length = Integer.MAX_VALUE)
    private String groupName;

    @Column(name = "groupavatar")
    private byte[] groupAvatar;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner", nullable = false)
    private User owner;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public byte[] getGroupAvatar() {
        return groupAvatar;
    }

    public void setGroupAvatar(byte[] groupAvatar) {
        this.groupAvatar = groupAvatar;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}