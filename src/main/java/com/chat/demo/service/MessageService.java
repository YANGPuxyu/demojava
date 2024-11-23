package com.chat.demo.service;

import com.chat.demo.entity.DTO.MessageDto;
import com.chat.demo.entity.Message;
import com.chat.demo.repository.MessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    // 获取聊天室的所有消息
    public List<MessageDto> getMessagesByChatRoom(Long chatRoomId) {
        return messageRepository.findByChatRoomId(chatRoomId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 发送消息
    public MessageDto sendMessage(MessageDto messageDto) {
        Message message = convertToEntity(messageDto);

        // 设置消息发送时间为当前时间
        message.setCreatedAt(LocalDateTime.now());

        Message savedMessage = messageRepository.save(message);
        return convertToDto(savedMessage);
    }

    // 删除消息
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    // 将实体转换为 DTO
    private MessageDto convertToDto(Message message) {
        MessageDto messageDto = new MessageDto();
        BeanUtils.copyProperties(message, messageDto);
        return messageDto;
    }

    // 将 DTO 转换为实体
    private Message convertToEntity(MessageDto messageDto) {
        Message message = new Message();
        BeanUtils.copyProperties(messageDto, message);
        return message;
    }
}

