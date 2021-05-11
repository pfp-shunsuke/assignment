package com.assignment.controller;

import com.assignment.entity.ErrorResponseBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({SQLException.class})
    public ResponseEntity<Object> handleMyException(SQLException exception, WebRequest request) {

        return super.handleExceptionInternal(exception,
                createErrorResponseBody(exception, request),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }

    private ErrorResponseBody createErrorResponseBody(SQLException exception, WebRequest request) {

        ErrorResponseBody errorResponseBody = new ErrorResponseBody();
        String uri = ((ServletWebRequest) request).getRequest().getRequestURI();

        errorResponseBody.setExceptionOccurrenceTime(ZonedDateTime.now());
        errorResponseBody.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponseBody.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        errorResponseBody.setMessage(exception.getMessage());
        errorResponseBody.setPath(uri);

        return errorResponseBody;
    }
}
