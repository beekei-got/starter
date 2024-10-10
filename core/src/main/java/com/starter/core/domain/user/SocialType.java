package com.starter.core.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

@Getter
@AllArgsConstructor
public enum SocialType {

    GOOGLE("구글"),
    KAKAO("카카오"),
    NAVER("네이버");

    private final String name;

}
