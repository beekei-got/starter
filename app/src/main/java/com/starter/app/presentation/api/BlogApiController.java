package com.starter.app.presentation.api;

import com.starter.app.config.apiVersion.ApiVersion;
import com.starter.core.config.payload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiVersion(1)
@Tag(name = ApiController.BLOG_TAG)
@RequestMapping(path = ApiController.BLOG_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class BlogApiController {

    @Operation(summary = "1. 블로그 생성", description = "블로그를 생성합니다.")
    @GetMapping(name = "1. 블로그 생성")
    public ApiResponse<?> createBlog() {
        return ApiResponse.success();
    }

}
