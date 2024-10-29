package com.starter.core.domain.auth.service;

import com.starter.core.config.exception.*;
import com.starter.core.domain.auth.AuthToken;
import com.starter.core.domain.auth.AuthTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthTokenDomainService {

    private final AuthTokenRepository authTokenRepository;

    public AuthToken getAuthTokenById(UUID authTokenId) {
        return authTokenRepository.findById(authTokenId)
            .orElseThrow(() -> new NotExistDataException(ExceptionMessageType.NOT_EXIST_AUTH_TOKEN));
    }

    public AuthToken getAuthTokenByRefreshToken(String accessToken) {
        return authTokenRepository.findByRefreshToken(accessToken)
            .orElseThrow(() -> new NotExistDataException(ExceptionMessageType.NOT_EXIST_AUTH_TOKEN));
    }

    public void confirmPossibleIssue(AuthToken authToken) {
        if (authToken.isIssueExpired())
            throw new ExpiredDataException(ExceptionMessageType.EXPIRED_AUTH_TOKEN);
        if (authToken.isIssued())
            throw new AlreadyProcessedDataException(ExceptionMessageType.ISSUED_AUTH_TOKEN);
    }

    public void confirmPossibleReissue(AuthToken authToken) {
        if (!authToken.isIssued())
            throw new UnprocessedDataException(ExceptionMessageType.NOT_ISSUED_AUTH_TOKEN);
        if (!authToken.isAccessTokenExpired())
            throw new UnprocessedDataException(ExceptionMessageType.NOT_EXPIRED_ACCESS_TOKEN);
        if (authToken.isRefreshTokenExpired())
            throw new AlreadyProcessedDataException(ExceptionMessageType.EXPIRED_REFRESH_TOKEN);
    }

}
