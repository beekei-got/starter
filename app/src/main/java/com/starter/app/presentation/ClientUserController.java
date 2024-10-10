package com.starter.app.presentation;

import com.starter.app.application.ClientUserInfoServiceV1;
import com.starter.app.config.apiVersion.ApiVersion;
import com.starter.app.config.payload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Tag(name = "02. 사용자 회원")
@ApiVersion(1)
@RestController
@RequestMapping(value = "user/client", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClientUserController {

	private final ClientUserInfoServiceV1 clientUserInfoServiceV1;

	@Operation(summary = "사용자 회원 정보 조회", description = "사용자 회원 정보를 조회합니다.")
	@GetMapping(name = "사용자 회원 정보 조회", value = "{userId}")
	public ApiResponse<?> signIn(@PathVariable @Valid @Min(1) long userId) {
		clientUserInfoServiceV1.getClientUserInfo(userId);
		return ApiResponse.success();
	}

}
