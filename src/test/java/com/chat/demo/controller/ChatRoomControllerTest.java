package com.chat.demo.controller;

import com.chat.demo.entity.DTO.ChatRoomDto;
import com.chat.demo.service.ChatRoomService;
import com.chat.demo.response.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChatRoomController.class)
class ChatRoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatRoomService chatRoomService;

    @Test
    void testCreateChatRoom() throws Exception {
        ChatRoomDto chatRoomDto = new ChatRoomDto();
        chatRoomDto.setId(1L);
        chatRoomDto.setName("Example Room");
        chatRoomDto.setCourseId(123L);

        when(chatRoomService.createChatRoom(any(ChatRoomDto.class))).thenReturn(chatRoomDto);

        mockMvc.perform(post("/chat-rooms")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Example Room\",\"courseId\":123}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.name").value("Example Room"));
    }

    @Test
    void testGetAllChatRooms() throws Exception {
        ChatRoomDto chatRoomDto = new ChatRoomDto();
        chatRoomDto.setId(1L);
        chatRoomDto.setName("Example Room");

        when(chatRoomService.getAllChatRooms()).thenReturn(List.of(chatRoomDto));

        mockMvc.perform(get("/chat-rooms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data[0].name").value("Example Room"));
    }

    @Test
    void testGetChatRoomById() throws Exception {
        ChatRoomDto chatRoomDto = new ChatRoomDto();
        chatRoomDto.setId(1L);
        chatRoomDto.setName("Example Room");

        when(chatRoomService.getChatRoomById(1L)).thenReturn(chatRoomDto);

        mockMvc.perform(get("/chat-rooms/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.name").value("Example Room"));
    }

    @Test
    void testDeleteChatRoom() throws Exception {
        when(chatRoomService.deleteChatRoom(1L)).thenReturn(true);

        mockMvc.perform(delete("/chat-rooms/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    void testUpdateChatRoom() throws Exception {
        ChatRoomDto chatRoomDto = new ChatRoomDto();
        chatRoomDto.setId(1L);
        chatRoomDto.setName("Updated Room");

        when(chatRoomService.updateChatRoom(Mockito.eq(1L), any(ChatRoomDto.class))).thenReturn(chatRoomDto);

        mockMvc.perform(put("/chat-rooms/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated Room\",\"courseId\":456}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.name").value("Updated Room"));
    }
}
