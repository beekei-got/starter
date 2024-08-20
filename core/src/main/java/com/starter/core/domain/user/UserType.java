package com.starter.core.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.management.relation.Role;
import java.util.Set;

@Getter
@AllArgsConstructor
public enum UserType {

    CLIENT("사용자 회원", Set.of(UserRole.GUEST, UserRole.CLIENT_USER)),
    ADMIN("관리자 회원", Set.of(UserRole.GUEST, UserRole.ADMIN_USER));

    private final String name;
    private final Set<UserRole> roles;

}
