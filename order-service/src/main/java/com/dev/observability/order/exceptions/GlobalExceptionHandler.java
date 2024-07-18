package com.dev.observability.order.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {RequestException.class})
    public ResponseEntity<Object> requestException(RequestException exception, WebRequest request) {
        return errorResponse(HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler(value = {InternalServerErrorException.class})
    public ResponseEntity<Object> internalServerErrorException(InternalServerErrorException exception, WebRequest request) {
        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception);
    }

    private ResponseEntity<Object> errorResponse(HttpStatus status, Throwable e) {
        return ResponseEntity
                .status(status)
                .body(new GlobalErrorResponse(status.value(), e.getMessage()));
    }

    private ResponseEntity<Object> errorResponse(HttpStatus status, List<String> errors, Throwable e) {
        return ResponseEntity
                .status(status)
                .body(new GlobalErrorResponse(status.value(), e.getMessage(), errors));
    }

}
