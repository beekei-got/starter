package com.starter.user.presentation;

import com.starter.user.application.AuthTokenServiceV1;
import com.starter.user.application.dto.AuthTokenDTO;
import com.starter.user.config.apiVersion.ApiVersion;
import com.starter.user.config.payload.ApiResponse;
import com.starter.user.presentation.request.IssueAuthTokenRequest;
import com.starter.user.presentation.response.IssueAuthTokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "01. 인증 토큰")
@ApiVersion(1)
@RestController
@RequestMapping(value = "token/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthTokenController {

    private final AuthTokenServiceV1 authTokenServiceV1;
    @Operation(summary = "인증토큰 발급", description = "인증토큰을 발급합니다.")
    @PostMapping(name = "인증토큰 발급", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<IssueAuthTokenResponse> issueAuthToken(@RequestBody @Valid IssueAuthTokenRequest request) {
        AuthTokenDTO authToken = authTokenServiceV1.issueAuthToken(request.getAuthTokenId());
        return ApiResponse.success(new IssueAuthTokenResponse(authToken));
    }

}
