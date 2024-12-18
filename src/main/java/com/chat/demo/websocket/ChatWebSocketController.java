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
        System.out.println("接收到前端消息: " + messageDto);

        // 保存消息到数据库
        try {
            MessageDto savedMessage = messageService.sendMessage(messageDto);
            System.out.println("消息已保存到数据库: " + savedMessage);

            // 将保存的消息推送到所有订阅了该聊天室的客户端
            String destination = "/topic/chat-room/" + savedMessage.getChatRoomId();
            messagingTemplate.convertAndSend(destination, savedMessage);
            System.out.println("消息已推送到: " + destination);

        } catch (Exception e) {
            // 捕获异常并打印错误日志
            System.err.println("处理消息时发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
