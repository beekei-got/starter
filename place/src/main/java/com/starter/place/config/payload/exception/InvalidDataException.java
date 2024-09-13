package com.starter.place.config.payload.exception;


import com.starter.place.config.payload.ApiResponseType;

public class InvalidDataException extends ApiException {

    public InvalidDataException(ExceptionMessageType exceptionMessageType) {
        super(ApiResponseType.INVALID_DATA, exceptionMessageType.getMessage());
    }

}
