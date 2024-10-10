package com.starter.core.config.exception;


public class UnprocessedDataException extends ApiException {

    public UnprocessedDataException(ExceptionMessageType exceptionMessageType) {
        super(ExceptionType.UNPROCESSED_DATA, exceptionMessageType.getMessage());
    }

}
