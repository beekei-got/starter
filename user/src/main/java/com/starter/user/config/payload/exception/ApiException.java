package com.starter.user.config.payload.exception;

import com.starter.user.config.payload.ApiResponseType;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final ApiResponseType apiResponseType;
    private final String message;

    public ApiException(ApiResponseType apiResponseType) {
        this.apiResponseType = apiResponseType;
        this.message = apiResponseType.getMessage();
    }

    public ApiException(ApiResponseType apiResponseType, String message) {
        this.apiResponseType = apiResponseType;
        this.message = message;
    }

}
