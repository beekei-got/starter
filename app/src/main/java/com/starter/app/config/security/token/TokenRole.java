package com.starter.app.config.security.token;

import com.starter.core.domain.user.UserType;
import lombok.Getter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public enum TokenRole {

    GUEST("게스트"),
    CLIENT_USER("사용자 회원", UserType.CLIENT),
    BUSINESS_USER("사업자 회원", UserType.BUSINESS),
    ADMIN_USER("관리자 회원", UserType.ADMIN);

    private final String name;
    private final UserType[] userTypes;

    TokenRole(String name, UserType ... userTypes) {
        this.name = name;
        this.userTypes = userTypes;
    }

    public static Set<TokenRole> ofUserType(UserType userType) {
        return Arrays.stream(TokenRole.values())
            .filter(tokenRole -> Set.of(tokenRole.getUserTypes()).contains(userType))
            .collect(Collectors.toSet());
    }

}
