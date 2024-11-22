package com.hallyugo.hallyugo.common.exception;

import lombok.Getter;

@Getter
public class InvalidFileException extends RuntimeException{
    private final int code;
    private final String message;

    public InvalidFileException(ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }
}