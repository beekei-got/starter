package com.starter.app.presentation;

import com.starter.app.application.AuthTokenServiceV1;
import com.starter.app.application.dto.AuthTokenDTO;
import com.starter.app.config.payload.ApiResponse;
import com.starter.app.presentation.request.IssueAuthTokenRequest;
import com.starter.app.presentation.response.IssueAuthTokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Tag(name = "01. 인증")
@RestController
@RequestMapping(value = "sign", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SignController {

	private final AuthTokenServiceV1 authTokenServiceV1;

	@Operation(summary = "로그인 화면")
	@GetMapping(name = "로그인 화면", value = "in")
	public ModelAndView signIn(@RequestParam String appRedirectUri) {
		ModelAndView modelAndView = new ModelAndView("sign-in");
		modelAndView.addObject("appRedirectUri", appRedirectUri);
		return modelAndView;
	}

	@Operation(summary = "인증토큰 발급", description = "인증토큰을 발급합니다.")
	@PostMapping(name = "인증토큰 발급", value = "auth-token", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse<IssueAuthTokenResponse> issueAuthToken(@RequestBody @Valid IssueAuthTokenRequest request) {
		AuthTokenDTO authToken = authTokenServiceV1.issueAuthToken(request.getAuthTokenId());
		return ApiResponse.success(new IssueAuthTokenResponse(authToken));
	}

}
