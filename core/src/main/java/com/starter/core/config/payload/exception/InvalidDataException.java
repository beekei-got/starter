package com.starter.core.config.payload.exception;


import com.starter.core.config.payload.ApiResponseType;

public class InvalidDataException extends ApiException {

    public InvalidDataException(ExceptionMessageType exceptionMessageType) {
        super(ApiResponseType.INVALID_DATA, exceptionMessageType.getMessage());
    }

}
