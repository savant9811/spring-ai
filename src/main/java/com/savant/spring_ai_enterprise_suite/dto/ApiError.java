package com.savant.spring_ai_enterprise_suite.dto;

public record ApiError(
        String field,
        String reason
) {
}
