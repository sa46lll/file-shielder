package com.flow.sa46lll.fileshield.adapter.in.web;

import com.flow.sa46lll.fileshield.dto.ApiResponse;
import com.flow.sa46lll.fileshield.entity.NotFoundException;
import com.flow.sa46lll.fileshield.exception.ExtensionDuplicationException;
import com.flow.sa46lll.fileshield.exception.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(ApiControllerAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected <T> ApiResponse<T> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("handleMethodArgumentNotValidException: ", e);
        return ApiResponse.failure(
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> ApiResponse<T> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.info("handleHttpMessageNotReadableException: ", e);
        return ApiResponse.failure(e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public <T> ApiResponse<T> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.info("handleHttpRequestMethodNotSupportedException: ", e);
        return ApiResponse.failure(e.getBody().getTitle());
    }

    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> ApiResponse<T> handleInvalidInputException(InvalidInputException e) {
        log.info("handleInvalidInputException: ", e);
        return ApiResponse.failure(e.getMessage());
    }

    @ExceptionHandler(ExtensionDuplicationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public <T> ApiResponse<T> handleExtensionDuplicationException(ExtensionDuplicationException e) {
        log.info("handleExtensionDuplicationException: ", e);
        return ApiResponse.failure(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public <T> ApiResponse<T> handleNotFoundException(NotFoundException e) {
        log.info("handleNotFoundException: ", e);
        return ApiResponse.failure(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public <T> ApiResponse<T> handleException(Exception e) {
        log.error("handleException: ", e);
        return ApiResponse.failure();
    }
}
