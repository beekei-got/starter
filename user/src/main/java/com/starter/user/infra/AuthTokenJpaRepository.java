package com.starter.user.infra;

import com.starter.user.domain.auth.AuthToken;
import com.starter.user.domain.auth.AuthTokenRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthTokenJpaRepository extends JpaRepository<AuthToken, UUID>, AuthTokenRepository {
}
