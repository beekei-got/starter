package com.starter.core.config.payload.exception;


import com.starter.core.config.payload.ApiResponseType;

public class NotExistDataException extends ApiException {

    public NotExistDataException(ExceptionMessageType exceptionMessageType) {
        super(ApiResponseType.NOT_EXIST_DATA, exceptionMessageType.getMessage());
    }

}
