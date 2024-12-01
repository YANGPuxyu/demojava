package com.chat.demo.service;

import com.chat.demo.entity.DTO.MessageDto;
import com.chat.demo.entity.Message;
import com.chat.demo.repository.MessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate; // 注入 WebSocket 消息推送工具
    // 获取聊天室的所有消息
    public List<MessageDto> getMessagesByChatRoom(Long chatRoomId) {
        return messageRepository.findByChatRoomId(chatRoomId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 发送消息
    public MessageDto sendMessage(MessageDto messageDto) {
        // 将消息 DTO 转换为实体类
        Message message = convertToEntity(messageDto);

        // 设置消息的发送时间
        message.setCreatedAt(LocalDateTime.now());

        // 保存消息到数据库
        Message savedMessage = messageRepository.save(message);

        // 将消息转换回 DTO
        MessageDto result = convertToDto(savedMessage);



        return result;
    }

    // 删除消息
    public boolean deleteMessage(Long id) {
        Optional<Message> message = messageRepository.findById(id);
        if (message.isPresent()) {
            messageRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // 将实体转换为 DTO
    private MessageDto convertToDto(Message message) {
        MessageDto messageDto = new MessageDto();
        BeanUtils.copyProperties(message, messageDto);
        messageDto.setCreatedAt(message.getCreatedAt().toString());
        return messageDto;
    }

    // 将 DTO 转换为实体
    private Message convertToEntity(MessageDto messageDto) {
        Message message = new Message();
        BeanUtils.copyProperties(messageDto, message);
        return message;
    }
}
