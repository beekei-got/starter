package com.starter.user.presentation;

import com.starter.user.application.ClientUserInfoServiceV1;
import com.starter.user.application.ClientUserSignServiceV1;
import com.starter.user.application.dto.ClientUserInfoDTO;
import com.starter.user.application.dto.ClientUserListDTO;
import com.starter.user.application.dto.GetClientUserListSearchFieldDTO;
import com.starter.user.config.apiVersion.ApiVersion;
import com.starter.user.config.payload.ApiResponse;
import com.starter.user.config.payload.PagingRequestDTO;
import com.starter.user.presentation.request.ResignClientUserRequest;
import com.starter.user.presentation.response.GetClientUserInfoResponse;
import com.starter.user.presentation.response.GetClientUserListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name = "02. 사용자 회원")
@ApiVersion(1)
@RestController
@RequestMapping(value = "user/client", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClientUserController {

    private final ClientUserSignServiceV1 clientUserSignServiceV1;
    private final ClientUserInfoServiceV1 clientUserInfoServiceV1;

    @Operation(summary = "사용자 회원 정보 조회", description = "사용자 회원의 정보를 조회합니다.")
    @GetMapping(name = "사용자 회원 정보 조회", value = "{userId}")
    public ApiResponse<GetClientUserInfoResponse> getClientUserInfo(@PathVariable @Valid @Min(1) long userId) {
        ClientUserInfoDTO clientUserInfo = clientUserInfoServiceV1.getClientUserInfo(userId);
        return ApiResponse.success(new GetClientUserInfoResponse(clientUserInfo));
    }

    @Operation(summary = "사용자 회원 목록 조회", description = "사용자 회원의 목록를 조회합니다.")
    @GetMapping(name = "사용자 회원 목록 조회")
    public ApiResponse<GetClientUserListResponse> getClientUserList(
        @ParameterObject @ModelAttribute GetClientUserListSearchFieldDTO searchField,
        @ParameterObject @ModelAttribute PagingRequestDTO paging) {
        Page<ClientUserListDTO> clientUserPage = clientUserInfoServiceV1.getClientUserList(searchField, paging.of());
        return ApiResponse.success(new GetClientUserListResponse(clientUserPage));
    }

    @Operation(summary = "사용자 회원 탈퇴", description = "사용자 회원을 탈퇴처리 합니다.")
    @DeleteMapping(name = "사용자 회원 탈퇴", value = "resign", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<?> resignClientUser(@RequestBody @Valid ResignClientUserRequest request) {
        clientUserSignServiceV1.resignClientUser(request.getUserId());
        return ApiResponse.success();
    }

}
