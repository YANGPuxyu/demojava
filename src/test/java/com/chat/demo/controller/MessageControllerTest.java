package com.chat.demo.controller;

import com.chat.demo.entity.DTO.MessageDto;
import com.chat.demo.response.Response;
import com.chat.demo.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(MessageController.class)
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    private MessageDto mockMessage;

    @BeforeEach
    void setUp() {
        mockMessage = new MessageDto();
        mockMessage.setId(101L);
        mockMessage.setChatRoomId(1L);
        mockMessage.setUserId(10L);
        mockMessage.setContent("Hello, World!");
        mockMessage.setMessageType("text");
        mockMessage.setCreatedAt("2024-11-24T12:00:00");
    }

    @Test
    void sendMessage_success() throws Exception {
        when(messageService.sendMessage(Mockito.any(MessageDto.class))).thenReturn(mockMessage);

        mockMvc.perform(post("/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"chatRoomId\":1,\"userId\":10,\"content\":\"Hello, World!\",\"messageType\":\"text\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.content").value("Hello, World!"));
    }

    @Test
    void getMessagesByChatRoom_success() throws Exception {
        when(messageService.getMessagesByChatRoom(1L)).thenReturn(Arrays.asList(mockMessage));

        mockMvc.perform(get("/messages/chat-room/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data[0].content").value("Hello, World!"));
    }

    @Test
    void deleteMessage_success() throws Exception {
        when(messageService.deleteMessage(101L)).thenReturn(true);

        mockMvc.perform(delete("/messages/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").value("消息删除成功"));
    }

    @Test
    void deleteMessage_failure() throws Exception {
        when(messageService.deleteMessage(999L)).thenReturn(false);

        mockMvc.perform(delete("/messages/999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.message").value("消息删除失败，消息不存在"));
    }
}
