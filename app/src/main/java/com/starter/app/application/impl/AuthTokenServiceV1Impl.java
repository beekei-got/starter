package com.starter.app.application.impl;

import com.starter.app.application.AuthTokenServiceV1;
import com.starter.app.application.dto.AuthTokenDTO;
import com.starter.core.domain.auth.AuthToken;
import com.starter.core.domain.auth.service.AuthTokenDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthTokenServiceV1Impl implements AuthTokenServiceV1 {

	private final AuthTokenDomainService authTokenDomainService;

	@Override
	public AuthTokenDTO issueAuthToken(UUID authTokenId) {
		AuthToken authToken = authTokenDomainService.getAuthToken(authTokenId);
		authTokenDomainService.confirmPossibleIssue(authToken);
		authToken.issue();
		return AuthTokenDTO.create(authToken);
	}

}
