package com.chattingweb.backend.entities.post;

import com.chattingweb.backend.entities.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long id;

    @Column(name = "post_image")
    private byte[] postImage;

    @Column(name = "post_text", length = Integer.MAX_VALUE)
    private String postText;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "post_date", nullable = false)
    private LocalDateTime postDate;

    @ColumnDefault("0")
    @Column(name = "no_like")
    private Integer noLike;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}