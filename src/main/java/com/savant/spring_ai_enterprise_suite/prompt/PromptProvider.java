package com.savant.spring_ai_enterprise_suite.prompt;

import org.springframework.ai.chat.prompt.Prompt;

public interface PromptProvider {

    Prompt chatPrompt(String userMessage);

    Prompt explainTopic(String topic, String difficulty, String language);

}
