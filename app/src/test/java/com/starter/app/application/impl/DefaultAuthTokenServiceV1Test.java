package com.starter.app.application.impl;

import com.starter.app.application.ApplicationServiceTest;
import com.starter.app.application.dto.AuthTokenDTO;
import com.starter.app.config.security.token.TokenDTO;
import com.starter.app.config.security.token.TokenProvider;
import com.starter.core.domain.auth.AuthToken;
import com.starter.core.domain.auth.AuthTokenMock;
import com.starter.core.domain.auth.AuthTokenRepository;
import com.starter.core.domain.auth.service.AuthTokenDomainService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

class DefaultAuthTokenServiceV1Test extends ApplicationServiceTest {

	@SpyBean
	@InjectMocks
	private DefaultAuthTokenServiceV1 authTokenServiceV1;

	@Mock
	private TokenProvider tokenProvider;

	@Mock
	private AuthTokenRepository authTokenRepository;

	@Mock
	private AuthTokenDomainService authTokenDomainService;

	@Test
	@DisplayName("인증토큰 등록")
	void saveAuthToken() {
		TokenDTO token = new TokenDTO("accessToken", LocalDateTime.now(), "refreshToken", LocalDateTime.now());
		given(tokenProvider.issueToken(any(UUID.class), anyLong(), anyString(), anySet())).willReturn(token);

		AuthToken authToken = AuthTokenMock.createAuthToken();
		given(authTokenRepository.saveAndFlush(any(AuthToken.class))).willReturn(authToken);

		long userId = 1L;
		String userName = "홍길동";
		Collection<? extends GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority("ROLE_USER"));
		UUID savedAuthTokenId = authTokenServiceV1.saveAuthToken(userId, userName, authorities);

		assertThat(savedAuthTokenId).isEqualTo(authToken.getId());
	}

	@Test
	@DisplayName("인증토큰 발급")
	void issueAuthToken() {
		AuthToken authToken = AuthTokenMock.createAuthToken();
		given(authTokenDomainService.getAuthTokenById(any(UUID.class))).willReturn(authToken);
		willDoNothing().given(authTokenDomainService).confirmPossibleIssue(authToken);

		UUID authTokenId = UUID.randomUUID();
		AuthTokenDTO authTokenDTO = authTokenServiceV1.issueAuthToken(authTokenId);

		assertThat(authToken.isIssued()).isTrue();
		assertThat(authTokenDTO.getAuthTokenId()).isEqualTo(authToken.getId());
		assertThat(authTokenDTO.getAccessToken()).isEqualTo(authToken.getAccessToken());
		assertThat(authTokenDTO.getRefreshToken()).isEqualTo(authToken.getRefreshToken());

		verify(authTokenDomainService).getAuthTokenById(authTokenId);
		verify(authTokenDomainService).confirmPossibleIssue(authToken);
	}

	@Test
	@DisplayName("인증토큰 재발급")
	void reissueAuthToken() {
		given(tokenProvider.validateRefreshToken(anyString())).willReturn(true);

		AuthToken originAuthToken = AuthTokenMock.createAuthToken();
		given(authTokenDomainService.getAuthTokenByRefreshToken(anyString())).willReturn(originAuthToken);
		willDoNothing().given(authTokenDomainService).confirmPossibleReissue(any(AuthToken.class));

		TokenDTO token = new TokenDTO("accessToken", LocalDateTime.now(), "refreshToken", LocalDateTime.now());
		given(tokenProvider.issueToken(any(UUID.class), anyLong(), anyString(), anySet())).willReturn(token);

		AuthToken authToken = AuthTokenMock.createIssuedAuthToken();
		given(authTokenRepository.saveAndFlush(any(AuthToken.class))).willReturn(authToken);

		String refreshToken = "refreshToken";
		AuthTokenDTO authTokenDTO = authTokenServiceV1.reissueAuthToken(refreshToken);

		assertThat(authToken.isIssued()).isTrue();
		assertThat(authTokenDTO.getAuthTokenId()).isEqualTo(authToken.getId());
		assertThat(authTokenDTO.getAccessToken()).isEqualTo(authToken.getAccessToken());
		assertThat(authTokenDTO.getRefreshToken()).isEqualTo(authToken.getRefreshToken());

		verify(tokenProvider).validateRefreshToken(refreshToken);
		verify(authTokenDomainService).getAuthTokenByRefreshToken(refreshToken);
		verify(authTokenDomainService).confirmPossibleReissue(originAuthToken);
	}

}