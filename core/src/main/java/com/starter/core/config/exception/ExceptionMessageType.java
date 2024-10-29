package com.starter.core.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessageType {

    NOT_EXIST_AUTH_TOKEN("등록된 인증 토큰이 없습니다."),
    ISSUED_AUTH_TOKEN("발급된 인증토큰 입니다."),
    EXPIRED_AUTH_TOKEN("발급이 만료된 인증토큰 입니다."),
    NOT_ISSUED_AUTH_TOKEN("발급되지 않은 인증토큰 입니다."),
    NOT_EXPIRED_ACCESS_TOKEN("만료되지 않은 AccessToken 입니다."),
    EXPIRED_REFRESH_TOKEN("만료된 RefreshToken 입니다."),

    UNSUPPORTED_USER_TYPE("지원하지 않는 회원유형입니다."),
    DUPLICATED_CLIENT_USER("중복된 사용자 회원입니다."),
    NOT_EXIST_USER("존재하지 않는 회원입니다."),
    BLOCK_USER("차단된 회원입니다."),
    RESIGN_USER("탈퇴된 회원입니다."),
    DUPLICATED_PHONE_NUMBER("중복된 휴대폰 번호 입니다."),
    DUPLICATED_CI("중복된 CI 입니다."),
    ;

    private final String message;

}
