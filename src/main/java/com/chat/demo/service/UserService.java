package com.chat.demo.service;

import com.chat.demo.entity.DTO.LoginRequestDto;
import com.chat.demo.entity.DTO.LoginResponseDto;
import com.chat.demo.entity.DTO.UserDto;
import com.chat.demo.entity.User;
import com.chat.demo.repository.UserRepository;
import com.chat.demo.utility.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // 获取所有用户并转换为 UserDto
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 根据 ID 获取用户并转换为 UserDto
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return (user != null) ? convertToDto(user) : null;
    }

    // 创建用户，将 UserDto 转换为 User
    // 创建用户，将 UserDto 转换为 User
    public UserDto createUser(User userDto) {
        User user = convertToEntity(userDto);

        // 设置创建时间和更新时间为当前时间
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }


    // 删除用户
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // 将 User 实体转换为 UserDto
    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    // 将 UserDto 转换为 User 实体
    private User convertToEntity(User userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 登录逻辑
    public LoginResponseDto login(LoginRequestDto loginRequest) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
        if (userOptional.isEmpty()) {
            return new LoginResponseDto("Invalid email", null, null, null);
        }

        User existingUser = userOptional.get();
        if (!passwordEncoder.matches(loginRequest.getPassword(), existingUser.getPassword())) {
            return new LoginResponseDto("Invalid password", null, null, null);
        }
        JwtUtil jwtUtil = new JwtUtil();
        // 使用 JwtUtil 生成 JWT Token
        String token = jwtUtil.generateToken(existingUser.getEmail(), existingUser.getRole());

        // 返回用户名、角色和 JWT Token
        return new LoginResponseDto("Login successful", token, existingUser.getName(), existingUser.getRole());
    }

}
