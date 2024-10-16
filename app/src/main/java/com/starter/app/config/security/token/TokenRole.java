package com.starter.app.config.security.token;

import com.starter.core.config.exception.ExceptionMessageType;
import com.starter.core.config.exception.UnsupportedTypeException;
import com.starter.core.domain.user.UserType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum TokenRole {

    GUEST("게스트", "GUEST", UserType.CLIENT, UserType.BUSINESS, UserType.ADMIN),
    CLIENT_USER("사용자 회원", "CLIENT_USER", UserType.CLIENT),
    BUSINESS_USER("사업자 회원", "BUSINESS_USER", UserType.BUSINESS),
    ADMIN_USER("관리자 회원", "ADMIN_USER", UserType.ADMIN);

    private final String name;
    private final String role;
    private UserType[] userTypes;

    TokenRole(String name, String role, UserType... userTypes) {
        this.name = name;
        this.role = role;
        this.userTypes = userTypes;
    }

    public GrantedAuthority getAuthority() {
        return new SimpleGrantedAuthority("ROLE_" + this.role);
    }

    public static Set<TokenRole> userTypeOf(UserType userType) {
        return Arrays.stream(TokenRole.values())
            .filter(role -> Set.of(role.getUserTypes()).contains(userType))
            .collect(Collectors.toSet());
    }

}
