package com.hallyugo.hallyugo.common.exception;

import lombok.Getter;

@Getter
public class AuthenticationNotFoundException extends RuntimeException{
    private final int code;
    private final String message;

    public AuthenticationNotFoundException(ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }
}
