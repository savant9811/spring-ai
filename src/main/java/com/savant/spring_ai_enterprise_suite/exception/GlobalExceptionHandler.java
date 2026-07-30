package com.savant.spring_ai_enterprise_suite.exception;

import com.savant.spring_ai_enterprise_suite.dto.ApiError;
import com.savant.spring_ai_enterprise_suite.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<ApiError>>> handleValidationException(
            MethodArgumentNotValidException exception) {

        List<ApiError> errors = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ApiError(
                        error.getField(),
                        error.getDefaultMessage()))
                .toList();

        ApiResponse<List<ApiError>> response = new ApiResponse<>(
                false,
                "Validation failed.",
                errors,
                Instant.now());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

}