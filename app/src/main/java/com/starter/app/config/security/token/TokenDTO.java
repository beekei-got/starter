package com.starter.app.config.security.token;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TokenDTO {
	private String accessToken;
	private LocalDateTime accessTokenExpiredDatetime;
	private String refreshToken;
	private LocalDateTime refreshTokenExpiredDatetime;
}
