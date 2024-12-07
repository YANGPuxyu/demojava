package com.chat.demo.websocket;


import com.chat.demo.entity.DTO.SignalMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebRtcController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // 接收 WebRTC Offer 信令并转发
    @MessageMapping("/sendOffer")
    public void handleOffer(SignalMessage signalMessage) {
        // 推送 Offer 信令给所有订阅该聊天室的客户端
        messagingTemplate.convertAndSend("/topic/video/" + signalMessage.getChatRoomId(), signalMessage);
    }

    // 接收 WebRTC Answer 信令并转发
    @MessageMapping("/sendAnswer")
    public void handleAnswer(SignalMessage signalMessage) {
        // 推送 Answer 信令给所有订阅该聊天室的客户端
        messagingTemplate.convertAndSend("/topic/video/" + signalMessage.getChatRoomId(), signalMessage);
    }

    // 接收 ICE Candidate 信令并转发
    @MessageMapping("/sendCandidate")
    public void handleCandidate(SignalMessage signalMessage) {
        // 推送 ICE Candidate 给所有订阅该聊天室的客户端
        messagingTemplate.convertAndSend("/topic/video/" + signalMessage.getChatRoomId(), signalMessage);
    }
}

