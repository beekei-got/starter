package com.starter.place.config.payload.exception;


import com.starter.place.config.payload.ApiResponseType;

public class NotAccessDataException extends ApiException {

    public NotAccessDataException(ExceptionMessageType exceptionMessageType) {
        super(ApiResponseType.NOT_ACCESS_DATA, exceptionMessageType.getMessage());
    }

}
