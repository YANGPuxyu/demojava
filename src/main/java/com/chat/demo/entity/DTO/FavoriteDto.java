package com.chat.demo.entity.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class FavoriteDto {

    private Long id;  // 收藏记录的自增ID
    @NotNull(message = "帖子ID不能为空")
    private Long postId;  // 关联的帖子ID
    @NotNull(message = "作者ID不能为空")
    private Long authorId;  // 用户ID，表示谁收藏了该帖子

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;  // 创建时间

    // 构造方法
    public FavoriteDto(Long id, Long postId, Long authorId, LocalDateTime createdAt) {
        this.id = id;
        this.postId = postId;
        this.authorId = authorId;
        this.createdAt = createdAt;
    }

    // 默认构造器
    public FavoriteDto() {}

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
