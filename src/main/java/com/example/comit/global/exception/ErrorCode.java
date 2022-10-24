package com.example.comit.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    TOKEN_EXPIRATION("토큰이 만료 되었습니다.", 401),
    TOKEN_NOT_VALID("토큰이 유효 하지 않습니다.", 401),
    USER_ALREADY_EXIST("이미 존재하는 유저입니다",409),
    UNKNOWN("알 수 없는 에러",500);

    private final String message;
    private final int status;
}
