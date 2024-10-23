package com.hallyugo.hallyugo.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthenticationNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleAuthenticationNotFoundException(AuthenticationNotFoundException e) {
        log.warn(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionResponse(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(InvalidJwtException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidJwtException(InvalidJwtException e) {
        log.warn(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionResponse(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ExceptionResponse> handleLoginException(LoginException e) {
        log.warn(e.getMessage(), e);
        return ResponseEntity.badRequest()
                .body(new ExceptionResponse(e.getCode(), e.getMessage()));
    }
}
