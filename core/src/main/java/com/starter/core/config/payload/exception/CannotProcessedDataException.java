package com.starter.core.config.payload.exception;


import com.starter.core.config.payload.ApiResponseType;

public class CannotProcessedDataException extends ApiException {

    public CannotProcessedDataException(ExceptionMessageType exceptionMessageType) {
        super(ApiResponseType.CANNOT_PROCESSED_DATA, exceptionMessageType.getMessage());
    }

}
