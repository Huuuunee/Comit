package com.example.comit.global.error.exception;

import com.example.comit.global.error.ErrorCode;
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