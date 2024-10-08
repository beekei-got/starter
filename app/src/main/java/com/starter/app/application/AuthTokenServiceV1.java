package com.starter.app.application;

import com.starter.app.application.dto.AuthTokenDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public interface AuthTokenServiceV1 {
	@Transactional
	AuthTokenDTO issueAuthToken(UUID authTokenId);
}
