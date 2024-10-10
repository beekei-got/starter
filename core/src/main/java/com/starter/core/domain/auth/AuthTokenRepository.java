package com.starter.core.domain.auth;

import java.util.Optional;
import java.util.UUID;

public interface AuthTokenRepository {
    AuthToken saveAndFlush(AuthToken authToken);
    Optional<AuthToken> findById(UUID authTokenId);
}
