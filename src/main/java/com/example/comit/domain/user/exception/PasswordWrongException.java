package com.example.comit.domain.user.exception;

import com.example.comit.global.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PasswordWrongException extends RuntimeException {
    private final ErrorCode errorCode;
    public PasswordWrongException(String message) {
        super(message);
        errorCode = ErrorCode.PASSWORD_WRONG;
    }
}