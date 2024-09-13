package com.starter.place.config.payload.exception;


import com.starter.place.config.payload.ApiResponseType;

public class UnprocessedDataException extends ApiException {

    public UnprocessedDataException(ExceptionMessageType exceptionMessageType) {
        super(ApiResponseType.UNPROCESSED_DATA, exceptionMessageType.getMessage());
    }

}
