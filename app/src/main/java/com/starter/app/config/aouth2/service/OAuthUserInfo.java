package com.starter.app.config.aouth2.service;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Map;

@ToString
@Getter
@Builder(access = AccessLevel.PROTECTED)
public class OAuthUserInfo {
    private String registrationId;
    private final Map<String, Object> attributes;
    private final String email;
    private final String name;
    private final String nickname;
    private final String gender;
    private final LocalDate birthday;
    private final String phoneNumber;
    private final String profileImageUrl;
}