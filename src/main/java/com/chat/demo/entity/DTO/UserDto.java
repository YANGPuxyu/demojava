package com.chat.demo.entity.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {
    private Long id; // 用户 ID

    @NotBlank(message = "Username cannot be empty")
    private String username; // 用户名

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email; // 邮箱

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password; // 密码

    @NotBlank(message = "Role cannot be empty")
    private String role; // 角色（学生、教师、管理员）

    // Getters and setters
       // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
