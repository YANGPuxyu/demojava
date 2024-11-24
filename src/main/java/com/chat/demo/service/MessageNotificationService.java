package com.chat.demo.service;

import com.chat.demo.entity.DTO.MessageNotificationDto;
import com.chat.demo.entity.MessageNotification;
import com.chat.demo.repository.MessageNotificationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageNotificationService {

    @Autowired
    private MessageNotificationRepository notificationRepository;

    // 获取用户所有通知
    public List<MessageNotificationDto> getNotificationsByUserId(Long userId) {
        List<MessageNotification> notifications = notificationRepository.findByUserId(userId);
        return notifications.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 标记通知为已读
    public MessageNotificationDto markNotificationAsRead(Long notificationId) {
        Optional<MessageNotification> notificationOpt = notificationRepository.findById(notificationId);
        if (notificationOpt.isPresent()) {
            MessageNotification notification = notificationOpt.get();
            notification.setIsRead(true);
            notificationRepository.save(notification);
            return convertToDto(notification);
        }
        return null; // 通知不存在时返回 null
    }

    // 创建通知
    public MessageNotificationDto createNotification(MessageNotificationDto notificationDto) {
        MessageNotification notification = convertToEntity(notificationDto);
        notification.setIsRead(false);
        notification.setNotifiedAt(LocalDateTime.now());

        MessageNotification savedNotification = notificationRepository.save(notification);
        return convertToDto(savedNotification);
    }

    // DTO 转换为 Entity
    private MessageNotification convertToEntity(MessageNotificationDto dto) {
        MessageNotification notification = new MessageNotification();
        BeanUtils.copyProperties(dto, notification);
        return notification;
    }

    // Entity 转换为 DTO
    private MessageNotificationDto convertToDto(MessageNotification notification) {
        MessageNotificationDto dto = new MessageNotificationDto();
        BeanUtils.copyProperties(notification, dto);
        return dto;
    }
}
