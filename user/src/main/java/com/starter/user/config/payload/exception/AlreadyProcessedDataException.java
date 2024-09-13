package com.starter.user.config.payload.exception;

import com.starter.user.config.payload.ApiResponseType;

public class AlreadyProcessedDataException extends ApiException {

    public AlreadyProcessedDataException(ExceptionMessageType exceptionMessageType) {
        super(ApiResponseType.ALREADY_PROCESSED_DATA, exceptionMessageType.getMessage());
    }

}
