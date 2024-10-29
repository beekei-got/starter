package com.starter.core.domain.auth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class AuthTokenTest {

    @Test
    @DisplayName("인증토큰 생성")
    void createAuthToken() {
        UUID authTokenId = UUID.randomUUID();
        long userId = 1;
        String userName = "홍길동";
        String accessToken = "accessTokenTest";
        LocalDateTime accessTokenExpiredDatetime = LocalDateTime.now().plusMinutes(10);
        String refreshToken = "refreshTokenTest";
        LocalDateTime refreshTokenExpiredDatetime = LocalDateTime.now().plusHours(1);
        Set<String> authorities = Set.of("CLIENT_USER");

        AuthToken authToken = AuthToken.createAuthToken(
            authTokenId, userId, userName,
            accessToken, accessTokenExpiredDatetime,
            refreshToken, refreshTokenExpiredDatetime,
            authorities, 1);

        assertThat(authToken.getId()).isEqualTo(authTokenId);
        assertThat(authToken.getUserId()).isEqualTo(userId);
        assertThat(authToken.getUserName()).isEqualTo(userName);
        assertThat(authToken.getAccessToken()).isEqualTo(accessToken);
        assertThat(authToken.getAccessTokenExpiredDatetime()).isEqualTo(accessTokenExpiredDatetime);
        assertThat(authToken.getRefreshToken()).isEqualTo(refreshToken);
        assertThat(authToken.getRefreshTokenExpiredDatetime()).isEqualTo(refreshTokenExpiredDatetime);
        assertThat(authToken.getAuthorities()).isEqualTo(authorities);
    }

    @Test
    @DisplayName("발급된 인증토큰 생성")
    void createIssuedAuthToken() {
        UUID authTokenId = UUID.randomUUID();
        long userId = 1;
        String userName = "홍길동";
        String accessToken = "accessTokenTest";
        LocalDateTime accessTokenExpiredDatetime = LocalDateTime.now().plusMinutes(10);
        String refreshToken = "refreshTokenTest";
        LocalDateTime refreshTokenExpiredDatetime = LocalDateTime.now().plusHours(1);
        Set<String> authorities = Set.of("CLIENT_USER");

        AuthToken authToken = AuthToken.createIssuedAuthToken(
            authTokenId, userId, userName,
            accessToken, accessTokenExpiredDatetime,
            refreshToken, refreshTokenExpiredDatetime,
            authorities);

        assertThat(authToken.getId()).isEqualTo(authTokenId);
        assertThat(authToken.getUserId()).isEqualTo(userId);
        assertThat(authToken.getUserName()).isEqualTo(userName);
        assertThat(authToken.getAccessToken()).isEqualTo(accessToken);
        assertThat(authToken.getAccessTokenExpiredDatetime()).isEqualTo(accessTokenExpiredDatetime);
        assertThat(authToken.getRefreshToken()).isEqualTo(refreshToken);
        assertThat(authToken.getRefreshTokenExpiredDatetime()).isEqualTo(refreshTokenExpiredDatetime);
        assertThat(authToken.getAuthorities()).isEqualTo(authorities);
    }

    @Test
    @DisplayName("인증토큰 발급 만료 여부 조회")
    void isIssueExpired() {
        AuthToken notExpiredAuthToken = AuthTokenMock.createAuthToken();
        assertThat(notExpiredAuthToken.isIssueExpired()).isFalse();

        AuthToken expiredAuthToken = AuthTokenMock.createIssueExpiredAuthToken();
        assertThat(expiredAuthToken.isIssueExpired()).isTrue();
    }

    @Test
    @DisplayName("인증토큰 AccessToken, RefreshToken 만료 여부 조회")
    void isExpiredAccessToken_isRefreshTokenExpired() {
        AuthToken notExpiredAuthToken = AuthTokenMock.createAuthToken();
        assertThat(notExpiredAuthToken.isAccessTokenExpired()).isFalse();
        assertThat(notExpiredAuthToken.isRefreshTokenExpired()).isFalse();

        AuthToken expiredAccessAuthToken = AuthTokenMock.createAccessTokenExpiredAuthToken();
        assertThat(expiredAccessAuthToken.isAccessTokenExpired()).isTrue();

        AuthToken expiredRefreshAuthToken = AuthTokenMock.createRefreshTokenExpiredAuthToken();
        assertThat(expiredRefreshAuthToken.isRefreshTokenExpired()).isTrue();
    }

    @Test
    @DisplayName("인증토큰 발급")
    void issue() {
        AuthToken authToken = AuthTokenMock.createAuthToken();
        authToken.issue();

        assertThat(authToken.isIssued()).isTrue();
        assertThat(authToken.getIssuedDatetime()).isNotNull();
    }

}