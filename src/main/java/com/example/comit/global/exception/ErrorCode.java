package com.example.comit.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNKNOWN("알 수 없는 에러",500);

    private final String message;
    private final int status;
}
