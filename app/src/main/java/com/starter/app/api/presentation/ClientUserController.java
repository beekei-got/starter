package com.starter.app.api.presentation;

import com.starter.app.config.apiVersion.ApiVersion;
import com.starter.app.config.payload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiVersion(1)
@Tag(name = ApiController.CLIENT_USER_TAG)
@RequestMapping(path = ApiController.CLIENT_USER_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ClientUserController {

    @Operation(summary = "1. 사용자 회원 정보 조회", description = "사용자 회원의 정보를 조회합니다.")
    @GetMapping(name = "1. 사용자 회원 정보 조회", value = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<?> getClientUserInfo(@PathVariable long userId) {
        System.out.println("userId : " + userId);
        return ApiResponse.success();
    }

}
