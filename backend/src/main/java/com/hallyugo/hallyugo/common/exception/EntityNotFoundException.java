package com.hallyugo.hallyugo.common.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {
    private final int code;
    private final String message;

    public EntityNotFoundException(ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }
}
