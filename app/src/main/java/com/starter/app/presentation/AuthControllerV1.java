package com.starter.app.presentation;

import com.beekei.library.apiVersion.ApiVersion;
import com.starter.app.application.AuthTokenServiceV1;
import com.starter.app.application.dto.AuthTokenDTO;
import com.starter.app.config.payload.ApiResponse;
import com.starter.app.presentation.request.IssueAuthTokenRequest;
import com.starter.app.presentation.request.ReissueAuthTokenRequest;
import com.starter.app.presentation.response.IssueAuthTokenResponse;
import com.starter.app.presentation.response.ReissueAuthTokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@ApiVersion(1)
@RestController
@RequiredArgsConstructor
@Tag(name = AppController.AUTH_TAG)
@RequestMapping(value = AppController.AUTH_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthControllerV1 {

	private final AuthTokenServiceV1 authTokenServiceV1;

	@Operation(summary = "인증토큰 발급", description = "인증토큰을 발급합니다.")
	@PostMapping(name = "인증토큰 발급", value = "token", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse<IssueAuthTokenResponse> issueAuthToken(@RequestBody @Valid IssueAuthTokenRequest request) {
		AuthTokenDTO authToken = authTokenServiceV1.issueAuthToken(request.getAuthTokenId());
		return ApiResponse.success(new IssueAuthTokenResponse(authToken));
	}

	@Operation(summary = "인증토큰 재발급", description = "AccessToken 만료 후 인증토큰을 재발급합니다.")
	@PutMapping(name = "인증토큰 재발급", value = "token", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse<ReissueAuthTokenResponse> reissueAuthToken(@RequestBody @Valid ReissueAuthTokenRequest request) {
		AuthTokenDTO authToken = authTokenServiceV1.reissueAuthToken(request.getAccessToken());
		return ApiResponse.success(new ReissueAuthTokenResponse(authToken));
	}

}
