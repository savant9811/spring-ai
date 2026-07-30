package com.savant.spring_ai_enterprise_suite.service;


import com.savant.spring_ai_enterprise_suite.dto.ChatRequest;
import com.savant.spring_ai_enterprise_suite.dto.ChatResponse;

public interface ChatService {

    ChatResponse chat(String message);

}
