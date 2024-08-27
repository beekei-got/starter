package com.starter.app.presentation.api.auth;

import com.starter.app.application.AuthTokenService;
import com.starter.app.application.dto.AuthTokenDTO;
import com.starter.app.presentation.api.ApiController;
import com.starter.app.presentation.api.auth.payload.request.IssueAuthTokenRequest;
import com.starter.app.config.apiVersion.ApiVersion;
import com.starter.core.config.payload.ApiResponse;
import com.starter.app.presentation.api.auth.payload.response.IssueAuthTokenResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@ApiVersion(1)
@Tag(name = ApiController.AUTH_TOKEN_TAG)
@RequestMapping(path = ApiController.AUTH_TOKEN_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class AuthTokenApiController {

    private final AuthTokenService authTokenService;

    @PostMapping(name = "토큰 발급", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<IssueAuthTokenResponse> issueToken(@RequestBody @Valid IssueAuthTokenRequest request) {
        AuthTokenDTO authToken = authTokenService.issueAuthToken(request.getAuthTokenId());
        return ApiResponse.success(new IssueAuthTokenResponse(authToken));
    }

}
