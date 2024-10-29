package com.starter.app.application.impl;

import com.starter.app.application.AuthTokenServiceV1;
import com.starter.app.application.dto.AuthTokenDTO;
import com.starter.app.config.security.token.TokenDTO;
import com.starter.app.config.security.token.TokenProvider;
import com.starter.core.domain.auth.AuthToken;
import com.starter.core.domain.auth.AuthTokenRepository;
import com.starter.core.domain.auth.service.AuthTokenDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DefaultAuthTokenServiceV1 implements AuthTokenServiceV1 {

	private final TokenProvider tokenProvider;
	private final AuthTokenRepository authTokenRepository;
	private final AuthTokenDomainService authTokenDomainService;

	@Override
	public UUID saveAuthToken(long userId, String userName, Collection<? extends GrantedAuthority> authorities) {
		UUID authTokenId = UUID.randomUUID();
		TokenDTO token = tokenProvider.issueToken(authTokenId, userId, userName, authorities);

		AuthToken authToken = authTokenRepository.saveAndFlush(AuthToken.createAuthToken(
			authTokenId, userId, userName,
			token.getAccessToken(), token.getAccessTokenExpiredDatetime(),
			token.getRefreshToken(), token.getRefreshTokenExpiredDatetime(),
			authorities.stream().map(Object::toString).collect(Collectors.toSet()),
			1
		));
		return authToken.getId();
	}

	@Override
	public AuthTokenDTO issueAuthToken(UUID authTokenId) {
		AuthToken authToken = authTokenDomainService.getAuthTokenById(authTokenId);
		authTokenDomainService.confirmPossibleIssue(authToken);
		authToken.issue();
		return AuthTokenDTO.create(authToken);
	}

	@Override
	public AuthTokenDTO reissueAuthToken(String refreshToken) {
		tokenProvider.validateRefreshToken(refreshToken);

		AuthToken originAuthToken = authTokenDomainService.getAuthTokenByRefreshToken(refreshToken);
		authTokenDomainService.confirmPossibleReissue(originAuthToken);

		UUID authTokenId = UUID.randomUUID();
		TokenDTO token = tokenProvider.issueToken(authTokenId,
			originAuthToken.getUserId(), originAuthToken.getUserName(),
			originAuthToken.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()));

		AuthToken authToken = authTokenRepository.saveAndFlush(AuthToken.createIssuedAuthToken(
			authTokenId, originAuthToken.getUserId(), originAuthToken.getUserName(),
			token.getAccessToken(), token.getAccessTokenExpiredDatetime(),
			token.getRefreshToken(), token.getRefreshTokenExpiredDatetime(),
			originAuthToken.getAuthorities().stream().map(Object::toString).collect(Collectors.toSet())
		));

		return AuthTokenDTO.create(authToken);
	}

}
