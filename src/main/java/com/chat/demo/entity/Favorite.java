package com.chat.demo.entity;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 收藏记录的自增ID

    @NotNull(message = "帖子ID不能为空")
    @Column(name = "post_id", nullable = false)
    private Long postId;  // 关联的帖子ID

    @NotNull(message = "作者ID不能为空")
    @Column(name = "author_id", nullable = false)
    private Long authorId;  // 用户ID，表示谁收藏了该帖子


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime createdAt;  // 创建时间

    // 默认构造器，创建时间自动生成
    public Favorite() {
        this.createdAt = LocalDateTime.now();  // 默认创建时间为当前时间
    }

    // Getter and Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

