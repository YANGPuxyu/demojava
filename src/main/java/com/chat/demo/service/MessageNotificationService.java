package com.chat.demo.service;

import com.chat.demo.entity.DTO.MessageNotificationDto;
import com.chat.demo.entity.MessageNotification;
import com.chat.demo.repository.MessageNotificationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
        MessageNotification notification = notificationRepository.findById(notificationId).orElse(null);
        if (notification != null) {
            notification.setIsRead(true);
            notificationRepository.save(notification);
        }
        return null;
    }

public MessageNotificationDto createNotification(MessageNotificationDto notificationDto) {
    // 转换为实体对象
    MessageNotification notification = convertToEntity(notificationDto);

    // 设置默认值
    notification.setIsRead(false);
    notification.setNotifiedAt(LocalDateTime.now());

    // 保存到数据库
    MessageNotification savedNotification = notificationRepository.save(notification);

    // 转换为 DTO 并返回
    return convertToDto(savedNotification);
}

// 将 DTO 转换为 Entity
private MessageNotification convertToEntity(MessageNotificationDto dto) {
    MessageNotification notification = new MessageNotification();
    BeanUtils.copyProperties(dto, notification);

    // 如果 DTO 中的时间是字符串形式，需转换为 LocalDateTime
    if (dto.getNotifiedAt() != null) {
        notification.setNotifiedAt(dto.getNotifiedAt());
    }
    return notification;
}


    // 将 Entity 转换为 DTO
    private MessageNotificationDto convertToDto(MessageNotification notification) {
        MessageNotificationDto dto = new MessageNotificationDto();
        BeanUtils.copyProperties(notification, dto);
        return dto;
    }

}
