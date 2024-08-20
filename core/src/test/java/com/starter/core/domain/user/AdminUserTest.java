package com.starter.core.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdminUserTest {

    @Test
    void 관리자_회원_생성() {
        String loginId = "admin123";
        String password = "11111";
        String name = "홍길동";
        AdminUser adminUser = AdminUser.createAdminUser(loginId, password, name);

        assertThat(adminUser.getLoginId()).isEqualTo(loginId);
        assertThat(adminUser.getPassword()).isEqualTo(password);
        assertThat(adminUser.getName()).isEqualTo(name);
        assertThat(adminUser.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

}