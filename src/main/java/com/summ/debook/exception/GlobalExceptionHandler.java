package com.summ.debook.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Serhii Tymoshenko
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (LOG.isErrorEnabled()) {
            if (ex instanceof NoHandlerFoundException) {
                LOG.error(ex.getMessage());
            } else {
                LOG.error(ex.getMessage(), ex);
            }
        }
        return new ResponseEntity<>(new ApiError(status, ex.getLocalizedMessage()), status);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        if (ex.getStatus().is4xxClientError() && LOG.isInfoEnabled()) {
            LOG.info(ex.getMessage());
        } else if (LOG.isErrorEnabled()) {
            LOG.error(ex.getMessage());
        }
        return new ResponseEntity<>(new ApiError(ex.getStatus(), ex.getReason()), ex.getStatus());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleAll(Throwable ex, WebRequest request) {
        if (LOG.isErrorEnabled()) {
            LOG.error(ex.getMessage(), ex);
        }
        return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
