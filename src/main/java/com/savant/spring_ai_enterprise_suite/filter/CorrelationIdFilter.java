package com.savant.spring_ai_enterprise_suite.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
public class CorrelationIdFilter extends OncePerRequestFilter {

    public static final String CORRELATION_ID = "correlationId";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String correlationId = request.getHeader("X-Correlation-ID");

        if (correlationId == null || correlationId.isBlank()) {
            correlationId = UUID.randomUUID().toString();
        }

        MDC.put(CORRELATION_ID, correlationId);

        response.setHeader("X-Correlation-ID", correlationId);

        long start = System.nanoTime();      // Start timer

        try {

            filterChain.doFilter(request, response);

        } finally {

            long duration = (System.nanoTime() - start) / 1_000_000;

            log.info(
                    "Request [{} {}] completed in {} ms",
                    request.getMethod(),
                    request.getRequestURI(),
                    duration
            );

            MDC.clear();

        }
    }
}