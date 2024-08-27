package com.starter.app.presentation.api.auth.payload.response;

import com.starter.app.application.dto.AuthTokenDTO;

public record IssueAuthTokenResponse(AuthTokenDTO authToken) {
}
