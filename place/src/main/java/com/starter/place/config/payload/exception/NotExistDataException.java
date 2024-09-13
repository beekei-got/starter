package com.starter.place.config.payload.exception;


import com.starter.place.config.payload.ApiResponseType;

public class NotExistDataException extends ApiException {

    public NotExistDataException(ExceptionMessageType exceptionMessageType) {
        super(ApiResponseType.NOT_EXIST_DATA, exceptionMessageType.getMessage());
    }

}
