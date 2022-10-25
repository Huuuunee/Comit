package com.example.comit.domain.auth.exception;

import com.example.comit.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;
    public UserNotFoundException(String message) {
        super(message);
        errorCode = ErrorCode.USER_NOT_FOUND;
    }
}