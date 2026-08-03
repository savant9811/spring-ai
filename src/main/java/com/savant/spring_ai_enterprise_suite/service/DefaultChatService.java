package com.savant.spring_ai_enterprise_suite.service;

import com.savant.spring_ai_enterprise_suite.dto.ChatResponse;
import com.savant.spring_ai_enterprise_suite.prompt.PromptProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class DefaultChatService implements ChatService {

    private static final Logger log = LoggerFactory.getLogger(DefaultChatService.class);

    private final ChatClient chatClient;
    private final PromptProvider promptProvider;

    public DefaultChatService(ChatClient chatClient, PromptProvider promptProvider) {
        this.chatClient = chatClient;
        this.promptProvider = promptProvider;
    }

    @Override
    public ChatResponse chat(String message) {

        Prompt prompt = promptProvider.chatPrompt(message);

        log.info("Sending prompt to Google Gemini.");

        String response = chatClient
                .prompt(prompt)
                .call()
                .content();

        log.info("AI response generated successfully.");

        return new ChatResponse(response);

    }

}
