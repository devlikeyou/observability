package com.dev.observability.notificator.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GlobalErrorResponse {

    private Integer statusCode;
    private String description;
    private List<String> errors;

    public GlobalErrorResponse(int statusCode, String description) {
        this.statusCode = statusCode;
        this.description = description;
    }

    public GlobalErrorResponse(int statusCode, String description, List<String> errors) {
        this.statusCode = statusCode;
        this.description = description;
        this.errors = errors;
    }

}
