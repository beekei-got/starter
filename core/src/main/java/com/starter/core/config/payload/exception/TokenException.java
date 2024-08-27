package com.starter.core.config.payload.exception;

import com.starter.core.config.payload.ApiResponseType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenException extends RuntimeException {

    private final ApiResponseType apiResponseType;

}
