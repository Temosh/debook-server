package com.summ.debook.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;

/**
 * @author Serhii Tymoshenko
 */
public class ApiError {

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime timestamp;
    private HttpStatus httpStatus;
    private String message;
    private List<String> errors;

    public ApiError(HttpStatus httpStatus) {
        this(httpStatus, null, (List<String>) null);
    }

    public ApiError(HttpStatus httpStatus, String message) {
        this(httpStatus, message, (List<String>) null);
    }

    public ApiError(HttpStatus httpStatus, String message, String error) {
        this(httpStatus, message, Collections.singletonList(error));
    }

    public ApiError(HttpStatus httpStatus, String message, List<String> errors) {
        this.timestamp = LocalDateTime.now(ZoneOffset.UTC);
        this.httpStatus = httpStatus;
        this.message = message;
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getHttpStatus() {
        return httpStatus.toString();
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
