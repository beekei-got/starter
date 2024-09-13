package com.starter.place.config.payload.exception;


import com.starter.place.config.payload.ApiResponseType;

public class ExpiredDataException extends ApiException {

    public ExpiredDataException(ExceptionMessageType exceptionMessageType) {
        super(ApiResponseType.EXPIRED_DATA, exceptionMessageType.getMessage());
    }

}
