package com.chat.demo.controller;

import com.chat.demo.entity.DTO.AttachmentDto;
import com.chat.demo.response.Response;
import com.chat.demo.service.AttachmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AttachmentControllerTest {

    @InjectMocks
    private AttachmentController attachmentController;

    @Mock
    private AttachmentService attachmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUploadAttachment_Success() {
        AttachmentDto mockAttachment = new AttachmentDto();
        mockAttachment.setId(1L);
        mockAttachment.setMessageId(100L);
        mockAttachment.setFileName("file.txt");

        when(attachmentService.uploadAttachment(any())).thenReturn(mockAttachment);

        Response<AttachmentDto> response = attachmentController.uploadAttachment(mockAttachment);

        assertEquals(200, response.getCode());
        assertNotNull(response.getData());
        assertEquals("file.txt", response.getData().getFileName());
        verify(attachmentService, times(1)).uploadAttachment(any());
    }

    @Test
    void testGetAttachmentsByMessage_NoAttachments() {
        when(attachmentService.getAttachmentsByMessage(100L)).thenReturn(Arrays.asList());

        Response<List<AttachmentDto>> response = attachmentController.getAttachmentsByMessage(100L);

        assertEquals(500, response.getCode());
        assertNull(response.getData());
        assertEquals("No attachments found for the message", response.getMessage());
        verify(attachmentService, times(1)).getAttachmentsByMessage(100L);
    }

    @Test
    void testDeleteAttachment_Success() {
        when(attachmentService.deleteAttachment(1L)).thenReturn(true);

        Response<Void> response = attachmentController.deleteAttachment(1L);

        assertEquals(200, response.getCode());
        assertNull(response.getData());
        verify(attachmentService, times(1)).deleteAttachment(1L);
    }

    @Test
    void testDeleteAttachment_Failure() {
        when(attachmentService.deleteAttachment(2L)).thenReturn(false);

        Response<Void> response = attachmentController.deleteAttachment(2L);

        assertEquals(500, response.getCode());
        assertEquals("Failed to delete attachment with id: 2", response.getMessage());
        verify(attachmentService, times(1)).deleteAttachment(2L);
    }
}
