package com.starter.core.domain.auth.service;

import com.starter.core.config.exception.AlreadyProcessedDataException;
import com.starter.core.config.exception.ExceptionMessageType;
import com.starter.core.config.exception.ExpiredDataException;
import com.starter.core.config.exception.NotExistDataException;
import com.starter.core.domain.DomainServiceTest;
import com.starter.core.domain.auth.AuthToken;
import com.starter.core.domain.auth.AuthTokenMock;
import com.starter.core.domain.auth.AuthTokenRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class AuthTokenDomainServiceTest extends DomainServiceTest {

    @SpyBean
    @InjectMocks
    private AuthTokenDomainService authTokenDomainService;

    @Mock
    private AuthTokenRepository authTokenRepository;

    @Test
    @DisplayName("인증토큰 등록")
    void saveAuthToken() {
        AuthToken savedAuthToken = AuthTokenMock.createToken();
        given(authTokenRepository.saveAndFlush(any(AuthToken.class))).willReturn(savedAuthToken);

        UUID authTokenId = authTokenDomainService.saveAuthToken(UUID.randomUUID(), 1,
            "accessTokenTest", LocalDateTime.now().plusMinutes(10),
            "refreshTokenTest", LocalDateTime.now().plusHours(1));

        verify(authTokenRepository).saveAndFlush(any(AuthToken.class));
        assertThat(authTokenId).isEqualTo(savedAuthToken.getId());
    }

    @Test
    @DisplayName("인증토큰 조회")
    void getAuthToken() {
        AuthToken savedAuthToken = AuthTokenMock.createToken();
        given(authTokenRepository.findById(any(UUID.class))).willReturn(Optional.of(savedAuthToken));

        UUID authTokenId = UUID.randomUUID();
        AuthToken authToken = authTokenDomainService.getAuthToken(authTokenId);

        verify(authTokenRepository).findById(authTokenId);
        assertThat(authToken).isEqualTo(savedAuthToken);
    }

    @Test
    @DisplayName("인증토큰 조회 - 존재하지 않는 인증토큰")
    void getAuthToken_notExistData() {
        given(authTokenRepository.findById(any(UUID.class))).willReturn(Optional.empty());

        UUID authTokenId = UUID.randomUUID();
        Throwable notExistDataException =
            assertThrows(NotExistDataException.class, () -> authTokenDomainService.getAuthToken(authTokenId));

        verify(authTokenRepository).findById(authTokenId);
        assertThat(notExistDataException.getMessage()).isEqualTo(ExceptionMessageType.NOT_EXIST_AUTH_TOKEN.getMessage());
    }

    @Test
    @DisplayName("인증토큰 발급 가능 여부 확인")
    void confirmPossibleIssue() {
        AuthToken authToken = AuthTokenMock.createToken();
        authTokenDomainService.confirmPossibleIssue(authToken);
    }

    @Test
    @DisplayName("인증토큰 발급 가능 여부 확인 - 만료된 인증토큰")
    void confirmPossibleIssue_expired() {
        AuthToken expiredAuthToken = AuthTokenMock.createExpiredToken();
        Throwable expiredDataException = assertThrows(ExpiredDataException.class,
            () -> authTokenDomainService.confirmPossibleIssue(expiredAuthToken));
        assertThat(expiredDataException.getMessage()).isEqualTo(ExceptionMessageType.EXPIRED_AUTH_TOKEN.getMessage());
    }

    @Test
    @DisplayName("인증토큰 발급 가능 여부 확인 - 이미 처리된 인증토큰")
    void confirmPossibleIssue_alreadyIssued() {
        AuthToken issuedAuthToken = AuthTokenMock.createIssuedToken();
        Throwable alreadyProcessedDataException = assertThrows(AlreadyProcessedDataException.class,
            () -> authTokenDomainService.confirmPossibleIssue(issuedAuthToken));
        assertThat(alreadyProcessedDataException.getMessage()).isEqualTo(ExceptionMessageType.ALREADY_ISSUED_AUTH_TOKEN.getMessage());
    }

}