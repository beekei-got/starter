package com.starter.user.config.payload.exception;

import com.starter.user.config.payload.ApiResponseType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class TokenException extends AuthenticationException {

    private final ApiResponseType apiResponseType;

    public TokenException(ApiResponseType apiResponseType) {
        super(apiResponseType.getMessage());
        this.apiResponseType = apiResponseType;
    }

}
