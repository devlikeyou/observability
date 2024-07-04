package com.dev.observability.notificator.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class GlobalException extends RuntimeException {

    @Getter
    private HttpStatus statusCode;

    public GlobalException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
