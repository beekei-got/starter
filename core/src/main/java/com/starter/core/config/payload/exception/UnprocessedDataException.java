package com.starter.core.config.payload.exception;


import com.starter.core.config.payload.ApiResponseType;

public class UnprocessedDataException extends ApiException {

    public UnprocessedDataException(ExceptionMessageType exceptionMessageType) {
        super(ApiResponseType.UNPROCESSED_DATA, exceptionMessageType.getMessage());
    }

}
