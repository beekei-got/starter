package com.starter.auth.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

@Getter
@AllArgsConstructor
public enum SocialType {

    GOOGLE("구글", "google", "sub", (attributes) -> OAuthUser.builder()
        .attributes(attributes)
        .name(String.valueOf(attributes.get("family_name")) + attributes.get("given_name"))
        .nickname(String.valueOf(attributes.get("name")))
        .email(String.valueOf(attributes.get("email")))
        .profileImageUrl(String.valueOf(attributes.get("picture")))
        .build()),
    KAKAO("카카오", "kakao", "id", (attributes) -> {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");
        return OAuthUser.builder()
            .attributes(attributes)
            .name(String.valueOf(kakaoProfile.get("nickname")))
            .email(String.valueOf(kakaoAccount.get("email")))
            .gender(String.valueOf(kakaoAccount.get("gender")))
            .profileImageUrl(String.valueOf(kakaoProfile.get("profile_image_url")))
            .build();
    }),
    NAVER("네이버", "naver", "id", (attributes) -> {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return OAuthUser.builder()
            .attributes(attributes)
            .name(String.valueOf(response.get("nickname")))
            .email(String.valueOf(response.get("email")))
            .profileImageUrl(String.valueOf(response.get("profile_image")))
            .gender(String.valueOf(response.get("gender")))
            .build();
    });

    private final String name;
    private final String registrationId;
    private final String nameKey;
    private final Function<Map<String, Object>, OAuthUser> convertOAuthUser;

    public static SocialType registrationIdOf(String registrationId) {
        return Arrays.stream(SocialType.values())
            .filter(type -> type.getRegistrationId().equals(registrationId))
            .findFirst()
            .orElse(null);
    }

}
