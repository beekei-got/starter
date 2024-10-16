package com.starter.app.presentation;

import com.starter.app.config.apiVersion.ApiVersion;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "201. 음식점 매장")
@ApiVersion(1)
@RestController
@RequestMapping(value = "shop/restaurant", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RestaurantShopController {

}
