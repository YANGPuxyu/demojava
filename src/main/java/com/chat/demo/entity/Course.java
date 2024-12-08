package com.chat.demo.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键
    @Column(nullable = false, unique = true)
    private String name; // 课程名称
    @Column
    private String description; // 课程描述
    @Column
    private Double price; // 课程价格

    @ManyToOne
    @JoinColumn(name = "chat_room_id", referencedColumnName = "id")
    private ChatRoom chatRoom; // 聊天室，修改为多对一关系

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt; // 创建时间

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 更新时间

    // 新增字段
    @Column
    private Integer credits; // 学分

    @Column
    private String teacherName; // 老师名字

    @Column
    private LocalDateTime startDate; // 开始时间

    @Column
    private LocalDateTime endDate; // 结课时间

    @Column
    private Boolean hasExam; // 是否有考试

    @Column
    private LocalDateTime examDate; // 考试时间

    @Column
    private String location; // 教学地点

    @Column
    private Integer teachingSessions; // 授课次数

    // Getters 和 Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Boolean getHasExam() {
        return hasExam;
    }

    public void setHasExam(Boolean hasExam) {
        this.hasExam = hasExam;
    }

    public LocalDateTime getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDateTime examDate) {
        this.examDate = examDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getTeachingSessions() {
        return teachingSessions;
    }

    public void setTeachingSessions(Integer teachingSessions) {
        this.teachingSessions = teachingSessions;
    }
}
