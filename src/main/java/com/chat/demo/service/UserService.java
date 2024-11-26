package com.chat.demo.service;

import com.chat.demo.entity.DTO.UserDto;
import com.chat.demo.entity.User;
import com.chat.demo.repository.UserRepository;
import com.chat.demo.response.Response;
import com.chat.demo.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public Response<Map<String, Object>> login(UserDto userDto) {
        Optional<User> userOptional = userRepository.findByEmail(userDto.getEmail());

        if (userOptional.isEmpty() || !userOptional.get().getPassword().equals(userDto.getPassword())) {
            return Response.error("邮箱或密码错误");
        }

        // 用户验证通过，生成 Token
        User user = userOptional.get();
        String token = jwtUtil.generateToken(user.getName(), user.getRole());

        // 构建返回数据
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("user", mapToDto(user));
        responseData.put("token", token);

        return Response.success(responseData);
    }


    // 获取所有用户
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // 根据 ID 获取用户
    public Response<UserDto> getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return Response.error("用户不存在");
        }
        return Response.success(mapToDto(userOptional.get()));
    }

    // 注册新用户
    @Transactional
    public Response<UserDto> createUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            return Response.error("邮箱已注册");
        }

        User user = mapToEntity(userDto);
        user.setRole("用户"); // 默认角色
        User savedUser = userRepository.save(user);
        return Response.success(mapToDto(savedUser));
    }

    // 删除用户
    @Transactional
    public Response<String> deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return Response.error("用户不存在");
        }
        userRepository.deleteById(id);
        return Response.success("用户删除成功");
    }

    // DTO 转 Entity
    private User mapToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        return user;
    }

    // Entity 转 DTO
    private UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        return userDto;
    }
}
