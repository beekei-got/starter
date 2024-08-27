package com.starter.app.application.impl;

import com.starter.app.application.ServiceTest;
import com.starter.core.domain.auth.AuthToken;
import com.starter.core.domain.auth.AuthTokenDomainService;
import com.starter.core.domain.auth.AuthTokenMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

class AuthTokenServiceV1Test extends ServiceTest {

    @SpyBean
    @InjectMocks
    private AuthTokenServiceV1 authTokenServiceV1;

    @Mock
    private AuthTokenDomainService authTokenDomainService;

    @Test
    @DisplayName("인증토큰 발급")
    void issueAuthToken() {
        AuthToken authToken = AuthTokenMock.createToken();
        given(authTokenDomainService.getAuthToken(any(UUID.class))).willReturn(authToken);
        willDoNothing().given(authTokenDomainService).confirmPossibleIssue(any(AuthToken.class));

        authTokenServiceV1.issueAuthToken(UUID.randomUUID());

        assertThat(authToken.isIssued()).isTrue();
        assertThat(authToken.getIssuedDatetime()).isNotNull();
    }

}