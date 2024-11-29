package com.chat.demo.controller;

import com.chat.demo.entity.DTO.UserDto;
import com.chat.demo.response.Response;
import com.chat.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 登录接口，返回用户信息
    @PostMapping("/login")
    public Response<Map<String, Object>> login(@RequestBody UserDto userDto) {
        return userService.login(userDto);
    }

    // 获取所有用户
    @GetMapping
    public Response<List<UserDto>> getAllUsers() {
        return Response.success(userService.getAllUsers());
    }

    // 根据 ID 获取用户
    @GetMapping("/{id}")
    public Response<UserDto> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // 注册新用户
    @PostMapping("/register")
    public Response<UserDto> createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public Response<String> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
