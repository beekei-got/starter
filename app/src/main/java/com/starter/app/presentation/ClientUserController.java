package com.starter.app.presentation;

import com.starter.app.application.ClientUserInfoService;
import com.starter.app.application.dto.ClientUserInfoDTO;
import com.starter.app.config.apiVersion.ApiVersion;
import com.starter.app.config.payload.ApiResponse;
import com.starter.app.config.security.SecurityUtils;
import com.starter.app.config.security.token.AccessTokenRole;
import com.starter.app.config.security.token.TokenRole;
import com.starter.app.presentation.response.GetClientUserInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name = "101. 사용자 회원")
@ApiVersion(1)
@RestController
@RequestMapping(value = "user/client", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClientUserController {

	private final ClientUserInfoService clientUserInfoService;

	@AccessTokenRole(TokenRole.CLIENT_USER)
	@Operation(summary = "사용자 회원 정보 조회", description = "사용자 회원 정보를 조회합니다.")
	@GetMapping(name = "사용자 회원 정보 조회")
	public ApiResponse<GetClientUserInfoResponse> getClientUserInfo() {
		long userId = SecurityUtils.getTokenUserId();
		ClientUserInfoDTO clientUserInfo = clientUserInfoService.getClientUserInfo(userId);
		return ApiResponse.success(new GetClientUserInfoResponse(clientUserInfo));
	}

}
