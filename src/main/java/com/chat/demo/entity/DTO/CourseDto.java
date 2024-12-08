package com.chat.demo.entity.DTO;

import java.time.LocalDateTime;

public class CourseDto {

    private Long id;
    private String name;
    private String description;
    private Double price;

    // 新增字段
    private Integer credits; // 学分
    private String teacherName; // 老师名字
    private LocalDateTime startDate; // 开始时间
    private LocalDateTime endDate; // 结课时间
    private Boolean hasExam; // 是否有考试
    private LocalDateTime examDate; // 考试时间
    private String location; // 教学地点
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

    public void setChatRoomId(Long id) {
    }
}
