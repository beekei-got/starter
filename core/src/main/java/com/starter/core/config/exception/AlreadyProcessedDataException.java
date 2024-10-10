package com.starter.core.config.exception;

public class AlreadyProcessedDataException extends ApiException {

    public AlreadyProcessedDataException(ExceptionMessageType exceptionMessageType) {
        super(ExceptionType.ALREADY_PROCESSED_DATA, exceptionMessageType.getMessage());
    }

}
