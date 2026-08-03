package com.savant.spring_ai_enterprise_suite.prompt;

import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DefaultPromptProvider implements PromptProvider {

    private final ResourceLoader resourceLoader;

    public DefaultPromptProvider(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public Prompt chatPrompt(String question) {

        String systemText = loadTemplate("system/system-chat.st").render();

        String userText = loadTemplate("user/user-chat.st")
                .render(Map.of(
                        "question", question
                ));

        SystemMessage systemMessage = new SystemMessage(systemText);
        UserMessage userMessage = new UserMessage(userText);

        return new Prompt(
                List.of(
                        systemMessage,
                        userMessage
                )
        );
    }

    @Override
    public Prompt explainTopic(String topic, String difficulty, String language) {

        PromptTemplate template = loadTemplate("user/explain-topic.st");

        return template.create(
                Map.of(
                        "topic", topic,
                        "difficulty", difficulty,
                        "language", language
                )
        );
    }

    private PromptTemplate loadTemplate(String path) {

        Resource resource = resourceLoader.getResource(
                        "classpath:prompts/" + path);

        return new PromptTemplate(resource);
    }
}