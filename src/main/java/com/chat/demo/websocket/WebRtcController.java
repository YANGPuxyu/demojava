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
        // 调试：输出接收到的 Offer 信令
        System.out.println("收到 Offer 信令: " + signalMessage);

        // 推送 Offer 信令给所有订阅该聊天室的客户端
        String destination = "/topic/video/" + signalMessage.getChatRoomId();
        messagingTemplate.convertAndSend(destination, signalMessage);

        // 调试：输出推送的目标路径
        System.out.println("Offer 信令已推送到: " + destination);
    }

    // 接收 WebRTC Answer 信令并转发
    @MessageMapping("/sendAnswer")
    public void handleAnswer(SignalMessage signalMessage) {
        // 调试：输出接收到的 Answer 信令
        System.out.println("收到 Answer 信令: " + signalMessage);

        // 推送 Answer 信令给所有订阅该聊天室的客户端
        String destination = "/topic/video/" + signalMessage.getChatRoomId();
        messagingTemplate.convertAndSend(destination, signalMessage);

        // 调试：输出推送的目标路径
        System.out.println("Answer 信令已推送到: " + destination);
    }

    // 接收 ICE Candidate 信令并转发
    @MessageMapping("/sendCandidate")
    public void handleCandidate(SignalMessage signalMessage) {
        // 调试：输出接收到的 ICE Candidate 信令
        System.out.println("收到 ICE Candidate 信令: " + signalMessage);

        // 推送 ICE Candidate 给所有订阅该聊天室的客户端
        String destination = "/topic/video/" + signalMessage.getChatRoomId();
        messagingTemplate.convertAndSend(destination, signalMessage);

        // 调试：输出推送的目标路径
        System.out.println("ICE Candidate 信令已推送到: " + destination);
    }
}
