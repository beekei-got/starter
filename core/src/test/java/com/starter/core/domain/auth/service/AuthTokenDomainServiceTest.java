package com.starter.core.domain.auth.service;

import com.beekei.library.mockTest.DomainServiceTest;
import com.starter.core.config.exception.*;
import com.starter.core.domain.auth.AuthToken;
import com.starter.core.domain.auth.AuthTokenMock;
import com.starter.core.domain.auth.AuthTokenRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AuthTokenDomainServiceTest extends DomainServiceTest {

    @MockBean
    @InjectMocks
    private AuthTokenDomainService authTokenDomainService;

    @Mock
    private AuthTokenRepository authTokenRepository;

    @Test
    @DisplayName("ID로 인증토큰 조회")
    void getAuthTokenById() {
        UUID authTokenId = UUID.randomUUID();

        AuthToken savedAuthToken = AuthTokenMock.createAuthToken();
        given(authTokenRepository.findById(any(UUID.class))).willReturn(Optional.of(savedAuthToken));
        AuthToken authToken = authTokenDomainService.getAuthTokenById(authTokenId);
        assertThat(authToken).isEqualTo(savedAuthToken);

        given(authTokenRepository.findById(any(UUID.class))).willReturn(Optional.empty());
        Throwable notExistDataException =
            assertThrows(NotExistDataException.class, () -> authTokenDomainService.getAuthTokenById(authTokenId));
        assertThat(notExistDataException.getMessage()).isEqualTo(ExceptionMessageType.NOT_EXIST_AUTH_TOKEN.getMessage());

        verify(authTokenRepository, times(2)).findById(authTokenId);
    }

    @Test
    @DisplayName("RefreshToken으로 인증토큰 조회")
    void getAuthTokenByRefreshToken() {
        String refreshToken = "refreshToken";

        AuthToken savedAuthToken = AuthTokenMock.createAuthToken();
        given(authTokenRepository.findByRefreshToken(anyString())).willReturn(Optional.of(savedAuthToken));
        AuthToken authToken = authTokenDomainService.getAuthTokenByRefreshToken(refreshToken);
        assertThat(authToken).isEqualTo(savedAuthToken);

        given(authTokenRepository.findByRefreshToken(anyString())).willReturn(Optional.empty());
        Throwable notExistDataException =
            assertThrows(NotExistDataException.class, () -> authTokenDomainService.getAuthTokenByRefreshToken(refreshToken));
        assertThat(notExistDataException.getMessage()).isEqualTo(ExceptionMessageType.NOT_EXIST_AUTH_TOKEN.getMessage());

        verify(authTokenRepository, times(2)).findByRefreshToken(refreshToken);
    }

    @Test
    @DisplayName("인증토큰 발급 가능 여부 확인")
    void confirmPossibleIssue() {
        AuthToken authToken = AuthTokenMock.createAuthToken();
        authTokenDomainService.confirmPossibleIssue(authToken);

        AuthToken expiredAuthToken = AuthTokenMock.createIssueExpiredAuthToken();
        Throwable expiredDataException = assertThrows(ExpiredDataException.class,
            () -> authTokenDomainService.confirmPossibleIssue(expiredAuthToken));
        assertThat(expiredDataException.getMessage()).isEqualTo(ExceptionMessageType.EXPIRED_AUTH_TOKEN.getMessage());

        AuthToken issuedAuthToken = AuthTokenMock.createIssuedAuthToken();
        Throwable alreadyProcessedDataException = assertThrows(AlreadyProcessedDataException.class,
            () -> authTokenDomainService.confirmPossibleIssue(issuedAuthToken));
        assertThat(alreadyProcessedDataException.getMessage()).isEqualTo(ExceptionMessageType.ISSUED_AUTH_TOKEN.getMessage());
    }

    @Test
    @DisplayName("인증토큰 재발급 가능 여부 확인")
    void confirmPossibleReissue() {
        AuthToken accessTokenExpiredAuthToken = AuthTokenMock.createAccessTokenExpiredAuthToken();
        authTokenDomainService.confirmPossibleReissue(accessTokenExpiredAuthToken);

        AuthToken notIssuedAuthToken = AuthTokenMock.createAuthToken();
        Throwable exception1 = assertThrows(UnprocessedDataException.class,
            () -> authTokenDomainService.confirmPossibleReissue(notIssuedAuthToken));
        assertThat(exception1.getMessage()).isEqualTo(ExceptionMessageType.NOT_ISSUED_AUTH_TOKEN.getMessage());

        AuthToken issuedAuthToken = AuthTokenMock. createIssuedAuthToken();
        Throwable exception2 = assertThrows(UnprocessedDataException.class,
            () -> authTokenDomainService.confirmPossibleReissue(issuedAuthToken));
        assertThat(exception2.getMessage()).isEqualTo(ExceptionMessageType.NOT_EXPIRED_ACCESS_TOKEN.getMessage());

        AuthToken refreshTokenExpiredAuthToken = AuthTokenMock.createRefreshTokenExpiredAuthToken();
        Throwable exception3 = assertThrows(AlreadyProcessedDataException.class,
            () -> authTokenDomainService.confirmPossibleReissue(refreshTokenExpiredAuthToken));
        assertThat(exception3.getMessage()).isEqualTo(ExceptionMessageType.EXPIRED_REFRESH_TOKEN.getMessage());
    }

}