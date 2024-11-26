package com.chat.demo.controller;

import com.chat.demo.entity.DTO.UserDto;
import com.chat.demo.response.Response;
import com.chat.demo.service.UserService;
import com.chat.demo.utility.JwtAuthenticationFilter;
import com.chat.demo.utility.JwtUtil;
import com.chat.demo.utility.SecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
@Import(SecurityConfig.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private JwtUtil jwtUtil;
    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private ObjectMapper objectMapper;

//    @Test
//    public void testLogin() throws Exception {
//        UserDto mockUser = new UserDto(1L, "User", "user@example.com", "学生", null);
//
//        Mockito.when(userService.login(Mockito.any(UserDto.class)))
//                .thenReturn(Response.success(mockUser));
//
//        mockMvc.perform(post("/users/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(new UserDto(null, null, "user@example.com", null, "123456"))))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value(200))
//                .andExpect(jsonPath("$.data.username").value("User"));
//    }

    @Test
    public void testGetAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    public void testGetUserById_UserExists() throws Exception {
        UserDto mockUser = new UserDto(1L, "User", "user@example.com", "学生", null);
        Mockito.when(userService.getUserById(1L)).thenReturn(Response.success(mockUser));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.username").value("User"));
    }

    @Test
    public void testCreateUser() throws Exception {
        UserDto newUser = new UserDto(null, "NewUser", "newuser@example.com", "学生", "123456");
        UserDto savedUser = new UserDto(3L, "NewUser", "newuser@example.com", "学生", null);

        Mockito.when(userService.createUser(Mockito.any(UserDto.class)))
                .thenReturn(Response.success(savedUser));

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.username").value("NewUser"));
    }

    @Test
    public void testDeleteUser_UserExists() throws Exception {
        Mockito.when(userService.deleteUser(1L)).thenReturn(Response.success("用户删除成功"));

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").value("用户删除成功"));
    }

    @Test
    public void testDeleteUser_UserNotFound() throws Exception {
        Mockito.when(userService.deleteUser(99L)).thenReturn(Response.error("用户不存在"));

        mockMvc.perform(delete("/users/99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.message").value("用户不存在"));
    }
}
