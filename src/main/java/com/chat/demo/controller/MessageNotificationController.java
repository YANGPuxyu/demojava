package com.chat.demo.controller;
import com.chat.demo.entity.DTO.LoginRequestDto;
import com.chat.demo.entity.DTO.LoginResponseDto;
import com.chat.demo.entity.DTO.MessageNotificationDto;
import com.chat.demo.entity.DTO.UserDto;
import com.chat.demo.entity.MessageNotification;
import com.chat.demo.entity.User;
import com.chat.demo.service.MessageNotificationService;
import com.chat.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/message-notifications")
public class MessageNotificationController {

    @Autowired
    private MessageNotificationService messageNotificationService;

    @PostMapping
    public MessageNotificationDto createNotification(@RequestBody MessageNotificationDto notification) {
        return messageNotificationService.createNotification(notification);
    }

    @GetMapping("/user/{userId}")
    public List<MessageNotificationDto> getNotificationsByUser(@PathVariable Long userId) {
        return messageNotificationService.getNotificationsByUserId(userId);
    }

    @PutMapping("/{id}/read")
    public MessageNotificationDto markNotificationAsRead(@PathVariable Long id) {
        return messageNotificationService.markNotificationAsRead(id);
    }
}
