package com.example.comit.global.exception.exceptionCollection;

import com.example.comit.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UnknownException extends RuntimeException {
    private final ErrorCode errorCode;
    public UnknownException(String message) {
        super(message);
        errorCode = ErrorCode.UNKNOWN;
    }
}