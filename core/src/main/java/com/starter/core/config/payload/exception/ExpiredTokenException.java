package com.starter.core.config.payload.exception;


import com.starter.core.config.payload.ApiResponseType;

public class ExpiredTokenException extends TokenException {

    public ExpiredTokenException() {
        super(ApiResponseType.EXPIRED_TOKEN);
    }

}
