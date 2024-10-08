package com.starter.core.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

    GUEST("게스트", "ROLE_GUEST"),
    CLIENT_USER("사용자 회원", "ROLE_CLIENT_USER"),
    ADMIN_USER("관리자 회원", "ROLE_ADMIN_USER");

    private final String name;
    private final String key;

}
