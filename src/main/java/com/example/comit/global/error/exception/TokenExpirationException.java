package com.example.comit.global.error.exception;

import com.example.comit.global.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TokenExpirationException extends RuntimeException {
    private final ErrorCode errorCode;
    public TokenExpirationException(String message) {
        super(message);
        errorCode = ErrorCode.TOKEN_EXPIRATION;
    }
}