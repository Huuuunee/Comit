package com.example.comit.domain.user.exception;

import com.example.comit.global.error.ErrorCode;
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