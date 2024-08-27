package com.starter.core.config.payload.exception;


import com.starter.core.config.payload.ApiResponseType;

public class AlreadyProcessedDataException extends ApiException {

    public AlreadyProcessedDataException(ExceptionMessageType exceptionMessageType) {
        super(ApiResponseType.ALREADY_PROCESSED_DATA, exceptionMessageType.getMessage());
    }

}
