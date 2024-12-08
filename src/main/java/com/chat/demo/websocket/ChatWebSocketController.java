package com.chat.demo.websocket;

import com.chat.demo.entity.DTO.MessageDto;
import com.chat.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // 接收 WebSocket 消息并保存，然后推送给其他客户端
    @MessageMapping("/sendMessage")  // 监听 "/app/sendMessage" 的消息
    public void handleMessage(MessageDto messageDto) {

        // 保存消息到数据库
        MessageDto savedMessage = messageService.sendMessage(messageDto);

        // 将保存的消息推送到所有订阅了该聊天室的客户端
        messagingTemplate.convertAndSend("/topic/chat-room/" + savedMessage.getChatRoomId(), savedMessage);

    }
}
