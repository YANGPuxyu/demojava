package com.chat.demo.controller;
import com.chat.demo.entity.DTO.LoginRequestDto;
import com.chat.demo.entity.DTO.LoginResponseDto;
import com.chat.demo.entity.DTO.MessageDto;
import com.chat.demo.entity.DTO.UserDto;
import com.chat.demo.entity.Message;
import com.chat.demo.entity.User;
import com.chat.demo.service.MessageService;
import com.chat.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public MessageDto sendMessage(@RequestBody MessageDto message) {
        return messageService.sendMessage(message);
    }

    @GetMapping("/chat-room/{chatRoomId}")
    public List<MessageDto> getMessagesByChatRoom(@PathVariable Long chatRoomId) {
        return messageService.getMessagesByChatRoom(chatRoomId);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }
}
