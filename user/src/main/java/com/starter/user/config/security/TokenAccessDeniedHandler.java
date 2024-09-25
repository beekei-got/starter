package com.starter.user.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.user.config.payload.ApiResponse;
import com.starter.user.config.payload.ApiResponseType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.io.OutputStream;

public class TokenAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        try (OutputStream os = response.getOutputStream()) {
            final ApiResponseType apiResponseType = ApiResponseType.FORBIDDEN_TOKEN;

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(apiResponseType.getHttpStatus().value());

            objectMapper.writeValue(os, ApiResponse.exception(apiResponseType));
            os.flush();
        }
    }

}
