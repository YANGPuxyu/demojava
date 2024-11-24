package com.chat.demo.controller;

import com.chat.demo.entity.DTO.ChatRoomMemberDto;
import com.chat.demo.response.Response;
import com.chat.demo.service.ChatRoomMemberService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChatRoomMemberController.class)
public class ChatRoomMemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatRoomMemberService chatRoomMemberService;

    @Test
    void testAddMemberToChatRoom() throws Exception {
        ChatRoomMemberDto memberDto = new ChatRoomMemberDto();
        memberDto.setId(101L);
        memberDto.setChatRoomId(1L);
        memberDto.setUserId(2L);
        memberDto.setJoinedAt(String.valueOf(LocalDateTime.now()));

        Mockito.when(chatRoomMemberService.addMemberToChatRoom(Mockito.any()))
                .thenReturn(memberDto);

        mockMvc.perform(post("/chat-room-members")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"chatRoomId\": 1, \"userId\": 2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.id").value(101));
    }

    @Test
    void testGetMembersByChatRoom() throws Exception {
        ChatRoomMemberDto member1 = new ChatRoomMemberDto();
        member1.setId(101L);
        member1.setChatRoomId(1L);
        member1.setUserId(2L);
        member1.setJoinedAt(String.valueOf(LocalDateTime.now()));

        ChatRoomMemberDto member2 = new ChatRoomMemberDto();
        member2.setId(102L);
        member2.setChatRoomId(1L);
        member2.setUserId(3L);
        member2.setJoinedAt(String.valueOf(LocalDateTime.now()));

        List<ChatRoomMemberDto> members = Arrays.asList(member1, member2);

        Mockito.when(chatRoomMemberService.getMembersByChatRoom(1L))
                .thenReturn(members);

        mockMvc.perform(get("/chat-room-members/chat-room/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].id").value(101));
    }

    @Test
    void testRemoveMemberFromChatRoom() throws Exception {
        mockMvc.perform(delete("/chat-room-members/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").isEmpty());
    }
}
