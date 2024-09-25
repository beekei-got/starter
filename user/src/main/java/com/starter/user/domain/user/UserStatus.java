package com.starter.user.domain.user;

import com.starter.user.config.payload.exception.ApiException;
import com.starter.user.config.payload.exception.ExceptionMessageType;
import com.starter.user.config.payload.exception.NotAccessDataException;
import lombok.AllArgsConstructor;
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
