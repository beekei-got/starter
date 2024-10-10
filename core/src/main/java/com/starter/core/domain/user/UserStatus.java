package com.starter.core.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus {

    ACTIVE("활성화"),
    RESIGN("탈퇴"),
    BLOCK("차단");

    private final String name;

}
