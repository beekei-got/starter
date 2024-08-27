package com.starter.app.application.impl;

import com.starter.app.application.AuthTokenService;
import com.starter.app.application.dto.AuthTokenDTO;
import com.starter.core.domain.auth.AuthToken;
import com.starter.core.domain.auth.AuthTokenDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthTokenServiceV1 implements AuthTokenService {

    private final AuthTokenDomainService authTokenDomainService;

    @Override
    public AuthTokenDTO issueAuthToken(UUID authTokenId) {
        AuthToken authToken = authTokenDomainService.getAuthToken(authTokenId);
        authTokenDomainService.confirmPossibleIssue(authToken);
        authToken.issue();
        return AuthTokenDTO.create(authToken);
    }

}
