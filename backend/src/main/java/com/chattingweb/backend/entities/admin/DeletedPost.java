package com.chattingweb.backend.entities.admin;

import com.chattingweb.backend.entities.post.Post;
import com.chattingweb.backend.entities.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Setter
@Getter
@Entity
@Table(name = "deleted_post")
public class DeletedPost {
    @Id
    @Column(name = "post_id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Size(max = 200)
    @NotNull
    @Column(name = "delete_reason", nullable = false, length = 200)
    private String deleteReason;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;

}