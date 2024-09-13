package com.starter.user.application.impl;

import com.starter.user.application.AuthTokenServiceV1;
import com.starter.user.application.dto.AuthTokenDTO;
import com.starter.user.domain.auth.AuthToken;
import com.starter.user.domain.auth.service.AuthTokenDomainService;
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
