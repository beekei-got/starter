package com.starter.core.domain.auth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class AuthTokenTest {

    @Test
    @DisplayName("인증토큰 생성")
    void createToken_() {
        UUID authTokenId = UUID.randomUUID();
        long userId = 1;
        String accessToken = "accessTokenTest";
        LocalDateTime accessTokenExpiredDatetime = LocalDateTime.now().plusMinutes(10);
        String refreshToken = "refreshTokenTest";
        LocalDateTime refreshTokenExpiredDatetime = LocalDateTime.now().plusHours(1);
        int issueExpiredMinute = 5;
        AuthToken authToken = AuthToken.createToken(authTokenId, userId,
            accessToken, accessTokenExpiredDatetime,
            refreshToken, refreshTokenExpiredDatetime,
            issueExpiredMinute);

        assertThat(authToken.getId()).isEqualTo(authTokenId);
        assertThat(authToken.getUserId()).isEqualTo(userId);
        assertThat(authToken.getAccessToken()).isEqualTo(accessToken);
        assertThat(authToken.getAccessTokenExpiredDatetime()).isEqualTo(accessTokenExpiredDatetime);
        assertThat(authToken.getRefreshToken()).isEqualTo(refreshToken);
        assertThat(authToken.getRefreshTokenExpiredDatetime()).isEqualTo(refreshTokenExpiredDatetime);
    }

    @Test
    @DisplayName("인증토큰 만료 여부")
    void isExpired() {
        AuthToken notExpiredAuthToken = AuthTokenMock.createToken();
        assertThat(notExpiredAuthToken.isExpired()).isFalse();

        AuthToken expiredAuthToken = AuthTokenMock.createExpiredToken();
        assertThat(expiredAuthToken.isExpired()).isTrue();
    }

    @Test
    @DisplayName("인증토큰 발급")
    void issue() {
        AuthToken authToken = AuthTokenMock.createToken();
        authToken.issue();

        assertThat(authToken.isIssued()).isTrue();
        assertThat(authToken.getIssuedDatetime()).isNotNull();
    }

}