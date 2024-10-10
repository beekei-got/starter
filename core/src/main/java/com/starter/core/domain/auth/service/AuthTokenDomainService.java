package com.starter.core.domain.auth.service;

import com.starter.core.config.exception.AlreadyProcessedDataException;
import com.starter.core.config.exception.ExceptionMessageType;
import com.starter.core.config.exception.ExpiredDataException;
import com.starter.core.config.exception.NotExistDataException;
import com.starter.core.domain.auth.AuthToken;
import com.starter.core.domain.auth.AuthTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthTokenDomainService {

    private final AuthTokenRepository authTokenRepository;

    public UUID saveAuthToken(UUID authTokenId,
                              long userId,
                              String accessToken, LocalDateTime accessTokenExpiredDatetime,
                              String refreshToken, LocalDateTime refreshTokenExpiredDatetime) {
        AuthToken authToken = authTokenRepository.saveAndFlush(AuthToken.createToken(
            authTokenId,
            userId,
            accessToken, accessTokenExpiredDatetime,
            refreshToken, refreshTokenExpiredDatetime,
            1
        ));
        return authToken.getId();
    }

    public AuthToken getAuthToken(UUID authTokenId) {
        return authTokenRepository.findById(authTokenId)
            .orElseThrow(() -> new NotExistDataException(ExceptionMessageType.NOT_EXIST_AUTH_TOKEN));
    }

    public void confirmPossibleIssue(AuthToken authToken) {
        if (authToken.isExpired())
            throw new ExpiredDataException(ExceptionMessageType.EXPIRED_AUTH_TOKEN);
        if (authToken.isIssued())
            throw new AlreadyProcessedDataException(ExceptionMessageType.ALREADY_ISSUED_AUTH_TOKEN);
    }

}
