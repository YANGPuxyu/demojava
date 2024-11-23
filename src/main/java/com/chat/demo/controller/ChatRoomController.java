package com.chat.demo.controller;
import com.chat.demo.entity.ChatRoom;
import com.chat.demo.entity.DTO.ChatRoomDto;
import com.chat.demo.entity.DTO.LoginRequestDto;
import com.chat.demo.entity.DTO.LoginResponseDto;
import com.chat.demo.entity.DTO.UserDto;
import com.chat.demo.entity.User;
import com.chat.demo.service.ChatRoomService;
import com.chat.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/chat-rooms")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping
    public ChatRoomDto createChatRoom(@RequestBody ChatRoomDto chatRoom) {
        return chatRoomService.createChatRoom(chatRoom);
    }

    @GetMapping
    public List<ChatRoomDto> getAllChatRooms() {
        return chatRoomService.getAllChatRooms();
    }

    @GetMapping("/{id}")
    public ChatRoomDto getChatRoomById(@PathVariable Long id) {
        return chatRoomService.getChatRoomById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteChatRoom(@PathVariable Long id) {
        chatRoomService.deleteChatRoom(id);
    }

    @PutMapping("/{id}")
    public ChatRoomDto updateChatRoom(@PathVariable Long id, @RequestBody ChatRoomDto chatRoom) {
        return chatRoomService.updateChatRoom(id, chatRoom);
    }
}

