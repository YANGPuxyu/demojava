package com.chat.demo.controller;

import com.chat.demo.entity.DTO.MessageNotificationDto;
import com.chat.demo.response.Response;
import com.chat.demo.service.MessageNotificationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MessageNotificationController.class)
public class MessageNotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageNotificationService messageNotificationService;

    @Test
    void testCreateNotification() throws Exception {
        MessageNotificationDto dto = new MessageNotificationDto();
        dto.setId(101L);
        dto.setUserId(1L);
        dto.setMessageId(10L);
        dto.setIsRead(false);
        dto.setNotifiedAt(LocalDateTime.now());

        Mockito.when(messageNotificationService.createNotification(Mockito.any()))
                .thenReturn(dto);

        mockMvc.perform(post("/message-notifications")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\": 1, \"messageId\": 10}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.id").value(101));
    }

    @Test
    void testGetNotificationsByUser() throws Exception {
        MessageNotificationDto dto1 = new MessageNotificationDto();
        dto1.setId(101L);
        dto1.setUserId(1L);
        dto1.setMessageId(10L);
        dto1.setIsRead(false);
        dto1.setNotifiedAt(LocalDateTime.now());

        MessageNotificationDto dto2 = new MessageNotificationDto();
        dto2.setId(102L);
        dto2.setUserId(1L);
        dto2.setMessageId(15L);
        dto2.setIsRead(true);
        dto2.setNotifiedAt(LocalDateTime.now());

        Mockito.when(messageNotificationService.getNotificationsByUserId(1L))
                .thenReturn(Arrays.asList(dto1, dto2));

        mockMvc.perform(get("/message-notifications/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].id").value(101));
    }

    @Test
    void testMarkNotificationAsRead() throws Exception {
        MessageNotificationDto dto = new MessageNotificationDto();
        dto.setId(101L);
        dto.setUserId(1L);
        dto.setMessageId(10L);
        dto.setIsRead(true);
        dto.setNotifiedAt(LocalDateTime.now());

        Mockito.when(messageNotificationService.markNotificationAsRead(101L))
                .thenReturn(dto);

        mockMvc.perform(put("/message-notifications/101/read"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.isRead").value(true));
    }

    @Test
    void testMarkNotificationAsRead_NotFound() throws Exception {
        Mockito.when(messageNotificationService.markNotificationAsRead(101L))
                .thenReturn(null);

        mockMvc.perform(put("/message-notifications/101/read"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.message").value("通知不存在或操作失败"));
    }
}
