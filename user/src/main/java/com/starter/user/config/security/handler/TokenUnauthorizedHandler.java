package com.starter.user.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.user.config.payload.ApiResponse;
import com.starter.user.config.payload.ApiResponseType;
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
        System.out.println("TokenUnauthorizedHandler");
        final ApiResponseType apiResponseType = ApiResponseType.valueOf(
            String.valueOf(Optional.ofNullable(request.getAttribute("ExceptionType"))
                .map(exceptionType -> ApiResponseType.valueOf(String.valueOf(exceptionType)))
                .orElse(ApiResponseType.INTERNAL_SERVER_ERROR)));
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(apiResponseType.getHttpStatus().value());
        response.getWriter().write(objectMapper.writeValueAsString(ApiResponse.exception(apiResponseType)));
    }

}
