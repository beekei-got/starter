package com.starter.place.config.payload.exception;


import com.starter.place.config.payload.ApiResponseType;

public class AlreadyProcessedDataException extends ApiException {

    public AlreadyProcessedDataException(ExceptionMessageType exceptionMessageType) {
        super(ApiResponseType.ALREADY_PROCESSED_DATA, exceptionMessageType.getMessage());
    }

}
