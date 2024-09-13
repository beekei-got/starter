package com.starter.user.domain.auth;


import java.time.LocalDateTime;
import java.util.UUID;


public class AuthTokenMock {

    public static AuthToken createToken() {
        AuthToken authToken = AuthToken.createToken(1,
            "accessTokenTest", LocalDateTime.now().plusMinutes(10),
            "refreshTokenTest", LocalDateTime.now().plusHours(1),
            5);
        authToken.id = UUID.randomUUID();
        return authToken;
    }

    public static AuthToken createExpiredToken() {
        AuthToken expiredAuthToken = AuthToken.createToken(1,
            "accessTokenTest", LocalDateTime.now().plusMinutes(10),
            "refreshTokenTest", LocalDateTime.now().plusHours(1),
            -1);
        expiredAuthToken.id = UUID.randomUUID();
        return expiredAuthToken;
    }

    public static AuthToken createIssuedToken() {
        AuthToken authToken = AuthTokenMock.createToken();
        authToken.issue();
        return authToken;
    }

}
