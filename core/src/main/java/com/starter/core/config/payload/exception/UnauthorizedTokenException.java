package com.starter.core.config.payload.exception;


import com.starter.core.config.payload.ApiResponseType;

public class UnauthorizedTokenException extends TokenException {

    public UnauthorizedTokenException() {
        super(ApiResponseType.UNAUTHORIZED_TOKEN);
    }

}
