package com.starter.app.presentation;

import com.starter.app.application.AuthTokenServiceV1;
import com.starter.app.application.dto.AuthTokenDTO;
import com.starter.core.domain.auth.AuthTokenMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;


class AuthControllerV1Test extends ControllerTest {

	@MockBean
	private AuthTokenServiceV1 authTokenServiceV1;

	protected AuthControllerV1Test() {
		super(AuthControllerV1.class);
	}

	@Test
	@DisplayName("인증토큰 발급")
	void issueAuthToken() {
		AuthTokenDTO authToken = AuthTokenDTO.create(AuthTokenMock.createAuthToken());
		given(authTokenServiceV1.issueAuthToken(any(UUID.class))).willReturn(authToken);

		mvcTest(HttpMethod.POST, "token")
			.requestBody(Map.entry("authTokenId", UUID.randomUUID()))
			.responseHttpStatus(HttpStatus.OK)
			.responseBody(
				Map.entry("authToken.authTokenId", authToken.getAuthTokenId().toString()),
				Map.entry("authToken.accessToken", authToken.getAccessToken()),
				Map.entry("authToken.refreshToken", authToken.getRefreshToken()))
			.run();
	}

	@Test
	@DisplayName("인증토큰 재발급")
	void reissueAuthToken() {
		AuthTokenDTO authToken = AuthTokenDTO.create(AuthTokenMock.createAuthToken());
		given(authTokenServiceV1.reissueAuthToken(anyString())).willReturn(authToken);

		mvcTest(HttpMethod.PUT, "token")
			.requestBody(Map.entry("refreshToken", "refreshToken"))
			.responseHttpStatus(HttpStatus.OK)
			.responseBody(
				Map.entry("authToken.authTokenId", authToken.getAuthTokenId().toString()),
				Map.entry("authToken.accessToken", authToken.getAccessToken()),
				Map.entry("authToken.refreshToken", authToken.getRefreshToken()))
			.run();
	}

}