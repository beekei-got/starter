package com.starter.core.infra;

import com.starter.core.domain.auth.AuthToken;
import com.starter.core.domain.auth.AuthTokenRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthTokenJpaRepository extends JpaRepository<AuthToken, UUID>, AuthTokenRepository {
}
