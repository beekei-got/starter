package com.starter.app.application;

import com.starter.app.application.dto.AuthTokenDTO;
import com.starter.core.domain.user.UserType;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.UUID;

@Service
public interface AuthTokenServiceV1 {
	@Transactional
	UUID saveAuthToken(long userId, String userName, Collection<? extends GrantedAuthority> authorities);
	@Transactional
	AuthTokenDTO issueAuthToken(UUID authTokenId);
	@Transactional
	AuthTokenDTO reissueAuthToken(String refreshToken);
}
