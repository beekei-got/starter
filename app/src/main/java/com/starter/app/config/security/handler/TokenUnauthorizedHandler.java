package com.starter.app.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.app.config.payload.ApiResponse;
import com.starter.core.config.exception.ExceptionType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class TokenUnauthorizedHandler implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        final ExceptionType exceptionType = ExceptionType.valueOf(
            String.valueOf(Optional.ofNullable(request.getAttribute("ExceptionType"))
                .map(et -> ExceptionType.valueOf(String.valueOf(et)))
                .orElse(ExceptionType.INTERNAL_SERVER_ERROR)));
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(exceptionType.getHttpStatus().value());
        response.getWriter().write(objectMapper.writeValueAsString(ApiResponse.exception(exceptionType)));
    }

}
