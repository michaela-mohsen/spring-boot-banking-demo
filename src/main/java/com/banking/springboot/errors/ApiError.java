package com.banking.springboot.errors;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiError(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus badRequest, String localizedMessage, String error) {
    }

    public HttpStatus getStatus() {
        return null;
    }

}
