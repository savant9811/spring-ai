package com.savant.spring_ai_enterprise_suite.dto;


import jakarta.validation.constraints.NotBlank;

public record ChatRequest(

        @NotBlank(message = "Message cannot be blank")
        String message

) {
}
