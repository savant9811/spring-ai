package com.savant.spring_ai_enterprise_suite.config;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "ai.chat")
@Data
public class AiProperties {

    @NotBlank
    private String model;

    @DecimalMin("0.0")
    @DecimalMax("2.0")
    private double temperature;

    @Min(1)
    private int maxTokens;

    @DecimalMin("0.0")
    @DecimalMax("1.0")
    private double topP;
}