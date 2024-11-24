package com.chat.demo.controller;

import com.chat.demo.entity.DTO.MessageNotificationDto;
import com.chat.demo.response.Response;
import com.chat.demo.service.MessageNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message-notifications")
public class MessageNotificationController {

    @Autowired
    private MessageNotificationService messageNotificationService;

    @PostMapping
    public Response<MessageNotificationDto> createNotification(@RequestBody MessageNotificationDto notification) {
        return Response.success(messageNotificationService.createNotification(notification));
    }

    @GetMapping("/user/{userId}")
    public Response<List<MessageNotificationDto>> getNotificationsByUser(@PathVariable Long userId) {
        return Response.success(messageNotificationService.getNotificationsByUserId(userId));
    }

    @PutMapping("/{id}/read")
    public Response<MessageNotificationDto> markNotificationAsRead(@PathVariable Long id) {
        MessageNotificationDto updatedNotification = messageNotificationService.markNotificationAsRead(id);
        if (updatedNotification != null) {
            return Response.success(updatedNotification);
        }
        return Response.error("通知不存在或操作失败");
    }
}
