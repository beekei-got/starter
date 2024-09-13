package com.starter.place.config.payload.exception;


import com.starter.place.config.payload.ApiResponseType;

public class DuplicatedDataException extends ApiException {

    public DuplicatedDataException(ExceptionMessageType exceptionMessageType) {
        super(ApiResponseType.DUPLICATED_DATA, exceptionMessageType.getMessage());
    }

}
