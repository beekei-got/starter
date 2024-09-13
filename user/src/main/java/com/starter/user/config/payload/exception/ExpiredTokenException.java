package com.starter.user.config.payload.exception;


import com.starter.user.config.payload.ApiResponseType;

public class ExpiredTokenException extends TokenException {

    public ExpiredTokenException() {
        super(ApiResponseType.EXPIRED_TOKEN);
    }

}
