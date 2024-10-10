package com.starter.app.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.app.config.payload.ApiResponse;
import com.starter.core.config.exception.ExceptionType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TokenAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        final ExceptionType exceptionType = ExceptionType.FORBIDDEN_TOKEN;
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(exceptionType.getHttpStatus().value());
        response.getWriter().write(objectMapper.writeValueAsString(ApiResponse.exception(exceptionType)));
    }

}
