package com.starter.core.domain.auth;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class AuthTokenMock {

    public static AuthToken createAuthToken() {
        AuthToken authToken = AuthToken.createAuthToken(
            UUID.randomUUID(), 1, "홍길동",
            "accessTokenTest", LocalDateTime.now().plusMinutes(10),
            "refreshTokenTest", LocalDateTime.now().plusHours(1),
            Set.of("CLIENT_USER"),
            1);
        authToken.id = UUID.randomUUID();
        return authToken;
    }

    public static AuthToken createIssueExpiredAuthToken() {
        AuthToken authToken = AuthToken.createAuthToken(
            UUID.randomUUID(), 1, "홍길동",
            "accessTokenTest", LocalDateTime.now().plusMinutes(10),
            "refreshTokenTest", LocalDateTime.now().plusHours(1),
            Set.of("CLIENT_USER"),
            -1);
        authToken.id = UUID.randomUUID();
        return authToken;
    }

    public static AuthToken createAccessTokenExpiredAuthToken() {
        AuthToken authToken = AuthToken.createAuthToken(
            UUID.randomUUID(), 1, "홍길동",
            "accessTokenTest", LocalDateTime.now().minusMinutes(10),
            "refreshTokenTest", LocalDateTime.now().plusHours(1),
            Set.of("CLIENT_USER"),
            1);
        authToken.id = UUID.randomUUID();
        authToken.issue();
        return authToken;
    }

    public static AuthToken createRefreshTokenExpiredAuthToken() {
        AuthToken authToken = AuthToken.createAuthToken(
            UUID.randomUUID(), 1, "홍길동",
            "accessTokenTest", LocalDateTime.now().minusMinutes(10),
            "refreshTokenTest", LocalDateTime.now().minusHours(1),
            Set.of("CLIENT_USER"),
            1);
        authToken.id = UUID.randomUUID();
        authToken.issue();
        return authToken;
    }

    public static AuthToken createIssuedAuthToken() {
        AuthToken authToken = AuthTokenMock.createAuthToken();
        authToken.issue();
        return authToken;
    }

}
