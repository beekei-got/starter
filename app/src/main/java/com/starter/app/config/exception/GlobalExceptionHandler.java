package com.starter.app.config.exception;

import com.starter.core.config.exception.ApiException;
import com.starter.app.config.payload.ApiResponse;
import com.starter.core.config.exception.ExceptionType;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Objects;
import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 지원하지 않는 Media type Exception
     */
    @ExceptionHandler(value = {
        HttpMediaTypeNotSupportedException.class,
        HttpRequestMethodNotSupportedException.class
    })
    public ResponseEntity<ApiResponse<?>> unSupportedMediaTypeExceptionHandler(Exception e) {
        e.printStackTrace();
        final ExceptionType exceptionType = ExceptionType.UNSUPPORTED_MEDIA_TYPE;
        return ResponseEntity
            .status(exceptionType.getHttpStatus())
            .body(ApiResponse.exception(exceptionType));
    }

    /**
     * 잘못된 API 요청 Exception
     */
    @ExceptionHandler(value = {
        NoHandlerFoundException.class,
        MethodArgumentNotValidException.class,
        MethodArgumentTypeMismatchException.class,
        HttpMessageNotReadableException.class,
        MissingServletRequestParameterException.class
    })
    public ResponseEntity<ApiResponse<?>> badRequestExceptionHandler(Exception e) {
        final ExceptionType exceptionType = ExceptionType.BAD_REQUEST;
        e.printStackTrace();
        if (e.getClass().equals(MethodArgumentNotValidException.class)) {
            MethodArgumentNotValidException notValidException = (MethodArgumentNotValidException) e;
            String fieldName = Objects.requireNonNull(notValidException.getFieldError()).getField();
            String message = Optional.ofNullable(notValidException.getFieldError())
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse(exceptionType.getMessage());
            return ResponseEntity
                .status(exceptionType.getHttpStatus())
                .body(ApiResponse.exception(exceptionType, fieldName + " : " + message));
        } else {
            return ResponseEntity
                .status(exceptionType.getHttpStatus())
                .body(ApiResponse.exception(exceptionType));
        }
    }

    /**
     * API Exception
     */
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ApiResponse<?>> apiExceptionHandler(ApiException e){
        return ResponseEntity
            .status(e.getExceptionType().getHttpStatus())
            .body(ApiResponse.exception(e.getExceptionType(), e.getMessage()));
    }

    /**
     * Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse<?>> exceptionHandler(Exception e){
        e.printStackTrace();
        ExceptionType exceptionType = ExceptionType.INTERNAL_SERVER_ERROR;
        return ResponseEntity
            .status(exceptionType.getHttpStatus())
            .body(ApiResponse.exception(exceptionType, exceptionType.getMessage()));
    }

}
