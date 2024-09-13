package com.starter.user.config.payload.exception;

import com.starter.user.config.payload.ApiResponseType;

public class NotExistDataException extends ApiException {

    public NotExistDataException(ExceptionMessageType exceptionMessageType) {
        super(ApiResponseType.NOT_EXIST_DATA, exceptionMessageType.getMessage());
    }

}
