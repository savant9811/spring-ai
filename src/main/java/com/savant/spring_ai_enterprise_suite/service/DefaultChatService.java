package com.savant.spring_ai_enterprise_suite.service;
import com.savant.spring_ai_enterprise_suite.dto.ChatRequest;
import com.savant.spring_ai_enterprise_suite.dto.ChatResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DefaultChatService implements ChatService {

    private static final Logger log = LoggerFactory.getLogger(DefaultChatService.class);

    private final ChatClient chatClient;
    private final PromptTemplateService templateService;

    public DefaultChatService(ChatClient chatClient, PromptTemplateService templateService) {
        this.chatClient = chatClient;
        this.templateService = templateService;
    }

    @Override
    public ChatResponse chat(String message) {

        PromptTemplate template = templateService.load("chat.st");

        Prompt prompt = template.create(
                        Map.of(
                                "question",
                                message
                        )
                );

        log.info("Sending prompt to Google Gemini.");

        String response = chatClient
                .prompt(prompt)
                .call()
                .content();

        log.info("AI response generated successfully.");

        return new ChatResponse(response);

    }

}
