package com.starter.core.config.payload.exception;


import com.starter.core.config.payload.ApiResponseType;

public class NotAccessDataException extends ApiException {

    public NotAccessDataException(ExceptionMessageType exceptionMessageType) {
        super(ApiResponseType.NOT_ACCESS_DATA, exceptionMessageType.getMessage());
    }

}
