package com.chat.demo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToMany;
import java.util.List;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import jakarta.persistence.PrePersist;


import java.time.LocalDateTime;

@Entity
@Table(name = "friendships") // 指定数据库表名为 friendships
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 用户

    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private User friend; // 好友

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt; // 好友关系建立时间

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}