package com.savant.spring_ai_enterprise_suite.prompt;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DefaultPromptProvider implements PromptProvider {

    private final ResourceLoader resourceLoader;

    public DefaultPromptProvider(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public Prompt chatPrompt(String userMessage) {

        PromptTemplate template = load("user/user-chat.st");

        return template.create(
                Map.of(
                        "question",
                        userMessage
                )
        );
    }

    @Override
    public Prompt explainTopic(String topic, String difficulty, String language) {

        PromptTemplate template = load("user/explain-topic.st");

        return template.create(
                Map.of(
                        "topic", topic,
                        "difficulty", difficulty,
                        "language", language
                )
        );
    }

    private PromptTemplate load(String templateName) {

        Resource resource = resourceLoader.getResource("classpath:prompts/" + templateName);
        return new PromptTemplate(resource);

    }

}
