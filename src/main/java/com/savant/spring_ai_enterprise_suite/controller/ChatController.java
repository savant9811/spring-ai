package com.savant.spring_ai_enterprise_suite.controller;

import com.savant.spring_ai_enterprise_suite.dto.ChatRequest;
import com.savant.spring_ai_enterprise_suite.dto.ChatResponse;
import com.savant.spring_ai_enterprise_suite.service.ChatService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@Valid @RequestBody ChatRequest request) {
        log.info("---inside chat method---");
        ChatResponse response = chatService.chat(request);
        return ResponseEntity.ok(response);
    }

}
