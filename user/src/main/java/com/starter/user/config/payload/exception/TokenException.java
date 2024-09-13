package com.starter.user.config.payload.exception;

import com.starter.user.config.payload.ApiResponseType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenException extends RuntimeException {

    private final ApiResponseType apiResponseType;

}
