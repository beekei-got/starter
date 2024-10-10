package com.starter.core.config.exception;


public class CannotProcessedDataException extends ApiException {

    public CannotProcessedDataException(ExceptionMessageType exceptionMessageType) {
        super(ExceptionType.CANNOT_PROCESSED_DATA, exceptionMessageType.getMessage());
    }

}
