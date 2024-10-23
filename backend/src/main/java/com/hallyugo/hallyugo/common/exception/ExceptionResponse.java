package com.hallyugo.hallyugo.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ExceptionResponse {
    private final int code;
    private final String message;
}
