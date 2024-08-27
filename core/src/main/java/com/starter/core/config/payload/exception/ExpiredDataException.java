package com.starter.core.config.payload.exception;


import com.starter.core.config.payload.ApiResponseType;

public class ExpiredDataException extends ApiException {

    public ExpiredDataException(ExceptionMessageType exceptionMessageType) {
        super(ApiResponseType.EXPIRED_DATA, exceptionMessageType.getMessage());
    }

}
