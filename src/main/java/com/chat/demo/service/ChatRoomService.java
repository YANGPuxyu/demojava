package com.chat.demo.service;
import com.chat.demo.entity.ChatRoom;
import com.chat.demo.entity.DTO.ChatRoomDto;
import com.chat.demo.entity.DTO.LoginRequestDto;
import com.chat.demo.entity.DTO.LoginResponseDto;
import com.chat.demo.entity.DTO.UserDto;
import com.chat.demo.entity.User;
import com.chat.demo.repository.ChatRoomRepository;
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
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    // 获取所有聊天室并转换为 DTO
    public List<ChatRoomDto> getAllChatRooms() {
        return chatRoomRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 根据 ID 获取聊天室并转换为 DTO
    public ChatRoomDto getChatRoomById(Long id) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElse(null);
        return (chatRoom != null) ? convertToDto(chatRoom) : null;
    }

    // 创建聊天室
    public ChatRoomDto createChatRoom(ChatRoomDto chatRoomDto) {
        ChatRoom chatRoom = convertToEntity(chatRoomDto);

        // 设置创建时间和更新时间为当前时间
        chatRoom.setCreatedAt(LocalDateTime.now());
        chatRoom.setUpdatedAt(LocalDateTime.now());

        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);
        return convertToDto(savedChatRoom);
    }

    // 删除聊天室
    public void deleteChatRoom(Long id) {
        chatRoomRepository.deleteById(id);
    }

    // 更新聊天室
    public ChatRoomDto updateChatRoom(Long id, ChatRoomDto chatRoomDto) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElseThrow(() -> new RuntimeException("ChatRoom not found"));

        BeanUtils.copyProperties(chatRoomDto, chatRoom, "id", "createdAt");
        chatRoom.setUpdatedAt(LocalDateTime.now());

        ChatRoom updatedChatRoom = chatRoomRepository.save(chatRoom);
        return convertToDto(updatedChatRoom);
    }

    // 将实体转换为 DTO
    private ChatRoomDto convertToDto(ChatRoom chatRoom) {
        ChatRoomDto chatRoomDto = new ChatRoomDto();
        BeanUtils.copyProperties(chatRoom, chatRoomDto);
        return chatRoomDto;
    }

    // 将 DTO 转换为实体
    private ChatRoom convertToEntity(ChatRoomDto chatRoomDto) {
        ChatRoom chatRoom = new ChatRoom();
        BeanUtils.copyProperties(chatRoomDto, chatRoom);
        return chatRoom;
    }
}
