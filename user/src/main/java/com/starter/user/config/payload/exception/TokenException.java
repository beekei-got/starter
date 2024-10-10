package com.starter.user.config.payload.exception;

import com.starter.user.config.payload.ApiResponseType;
import lombok.Getter;

@Getter
public class TokenException extends RuntimeException {

    private final ApiResponseType apiResponseType;

    public TokenException(ApiResponseType apiResponseType) {
        super(apiResponseType.getMessage());
        this.apiResponseType = apiResponseType;
    }

}
