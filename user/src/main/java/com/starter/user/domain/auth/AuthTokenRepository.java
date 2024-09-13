package com.starter.user.domain.auth;

import java.util.Optional;
import java.util.UUID;

public interface AuthTokenRepository {
    AuthToken saveAndFlush(AuthToken authToken);
    Optional<AuthToken> findById(UUID authTokenId);
}
