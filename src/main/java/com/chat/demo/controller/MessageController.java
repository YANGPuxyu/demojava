package com.chat.demo.controller;

import com.chat.demo.entity.DTO.MessageDto;
import com.chat.demo.response.Response;
import com.chat.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    // 发送消息
    @PostMapping
    public Response<MessageDto> sendMessage(@RequestBody MessageDto message) {
        MessageDto result = messageService.sendMessage(message);
        return Response.success(result);
    }

    // 获取聊天室的所有消息
    @GetMapping("/chat-room/{chatRoomId}")
    public Response<List<MessageDto>> getMessagesByChatRoom(@PathVariable Long chatRoomId) {
        List<MessageDto> messages = messageService.getMessagesByChatRoom(chatRoomId);
        return Response.success(messages);
    }

    // 删除消息
    @DeleteMapping("/{id}")
    public Response<String> deleteMessage(@PathVariable Long id) {
        boolean success = messageService.deleteMessage(id);
        if (success) {
            return Response.success("消息删除成功");
        } else {
            return Response.error("消息删除失败，消息不存在");
        }
    }
}
