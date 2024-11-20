package com.chat.demo.entity.DTO;

public class LoginResponseDto {

    private String status;
    private String token;
    private String name; // 用户名
    private String role; // 用户角色

    public LoginResponseDto(String status, String token, String name, String role) {
        this.status = status;
        this.token = token;
        this.name = name;
        this.role = role;
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
