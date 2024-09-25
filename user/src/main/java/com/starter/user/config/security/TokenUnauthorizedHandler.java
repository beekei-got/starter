package com.starter.user.config.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter.user.config.payload.ApiResponseType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

public class TokenUnauthorizedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        try (OutputStream os = response.getOutputStream()) {
            final ApiResponseType apiResponseType = ApiResponseType.valueOf(
                String.valueOf(Optional.ofNullable(request.getAttribute("ExceptionType"))
                    .map(exceptionType -> ApiResponseType.valueOf(String.valueOf(exceptionType)))
                    .orElse(ApiResponseType.INTERNAL_SERVER_ERROR)));

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(apiResponseType.getHttpStatus().value());

            os.flush();
        }
    }

}
