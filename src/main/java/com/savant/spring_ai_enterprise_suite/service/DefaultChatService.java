package com.savant.spring_ai_enterprise_suite.service;
import com.savant.spring_ai_enterprise_suite.dto.ChatRequest;
import com.savant.spring_ai_enterprise_suite.dto.ChatResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class DefaultChatService implements ChatService {

    private final ChatClient chatClient;

    public DefaultChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public ChatResponse chat(ChatRequest request) {

        String response = chatClient.prompt()
                .user(request.message())
                .call()
                .content();

        return new ChatResponse(response);
    }
}
