package com.csvdatanub.controller;

import com.csvdatanub.util.TimeUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Map<Class<? extends Exception>, HttpStatus> exceptions = Map.of(
            MethodArgumentNotValidException.class, HttpStatus.BAD_REQUEST,
            ParseException.class, HttpStatus.BAD_REQUEST
    );

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleException(Exception exception) {
        var status = exceptions.getOrDefault(exception.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(status)
                .body(errorResponse(status, getMessage(exception)));
    }

    private Map<String, Object> errorResponse(HttpStatus status, String message) {
        return Map.of(
                "timestamp", TimeUtils.formatter(LocalDateTime.now()),
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "message", message
        );
    }

    private String getMessage(Exception exception) {
        return Optional.of(exception)
                .filter(MethodArgumentNotValidException.class::isInstance)
                .map(MethodArgumentNotValidException.class::cast)
                .map(BindException::getBindingResult)
                .map(bindingResult ->
                        Optional.ofNullable(bindingResult.getFieldError())
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .orElseGet(() ->
                                        Optional.ofNullable(bindingResult.getGlobalError())
                                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                                .orElse(exception.getMessage())
                                )
                )
                .orElse(exception.getMessage());
    }

}
