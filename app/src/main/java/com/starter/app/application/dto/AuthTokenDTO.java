package com.starter.app.application.dto;

import com.starter.core.domain.auth.AuthToken;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class AuthTokenDTO {

    private final String accessToken;
    private final String refreshToken;

    public static AuthTokenDTO create(AuthToken authToken) {
        return AuthTokenDTO.builder()
            .accessToken(authToken.getAccessToken())
            .refreshToken(authToken.getRefreshToken())
            .build();
    }

}
