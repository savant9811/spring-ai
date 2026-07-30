package com.savant.spring_ai_enterprise_suite.service;


import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class PromptTemplateService {

    private final ResourceLoader resourceLoader;

    public PromptTemplateService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public PromptTemplate load(String templateName) {
        Resource resource = resourceLoader.getResource("classpath:prompts/" + templateName);

        return new PromptTemplate(resource);
    }
}