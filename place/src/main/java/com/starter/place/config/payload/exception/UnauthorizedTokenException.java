package com.starter.place.config.payload.exception;


import com.starter.place.config.payload.ApiResponseType;

public class UnauthorizedTokenException extends TokenException {

    public UnauthorizedTokenException() {
        super(ApiResponseType.UNAUTHORIZED_TOKEN);
    }

}
