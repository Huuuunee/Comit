package com.example.comit.global.exception.exceptionCollection;

import com.example.comit.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TokenNotValidException extends RuntimeException {
    private final ErrorCode errorCode;
    public TokenNotValidException(String message) {
        super(message);
        errorCode = ErrorCode.TOKEN_NOT_VALID;
    }
}