package com.starter.place.config.payload.exception;


import com.starter.place.config.payload.ApiResponseType;

public class ExpiredTokenException extends TokenException {

    public ExpiredTokenException() {
        super(ApiResponseType.EXPIRED_TOKEN);
    }

}
