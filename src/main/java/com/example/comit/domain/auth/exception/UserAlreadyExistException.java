package com.example.comit.domain.auth.exception;

import com.example.comit.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserAlreadyExistException extends RuntimeException {
    private final ErrorCode errorCode;
    public UserAlreadyExistException(String message) {
        super(message);
        errorCode = ErrorCode.USER_ALREADY_EXIST;
    }
}