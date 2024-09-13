package com.starter.user.config.payload.exception;


import com.starter.user.config.payload.ApiResponseType;

public class UnauthorizedTokenException extends TokenException {

    public UnauthorizedTokenException() {
        super(ApiResponseType.UNAUTHORIZED_TOKEN);
    }

}
