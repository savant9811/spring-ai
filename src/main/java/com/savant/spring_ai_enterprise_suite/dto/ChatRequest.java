package com.savant.spring_ai_enterprise_suite.dto;


import jakarta.validation.constraints.NotBlank;

public record ChatRequest(

        @NotBlank(message = "ConversationId cannot be blank")
        String conversationId,

        @NotBlank(message = "Message cannot be blank")
        String message


) {
}
