package com.starter.user.domain.user.admin;

import com.starter.user.domain.user.UserStatus;
import com.starter.user.domain.user.admin.AdminUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdminUserTest {

    @Test
    @DisplayName("관리자 회원 생성")
    void createUser() {
        String loginId = "admin123";
        String password = "11111";
        String name = "홍길동";
        AdminUser adminUser = AdminUser.createUser(loginId, password, name);

        assertThat(adminUser.getLoginId()).isEqualTo(loginId);
        assertThat(adminUser.getPassword()).isEqualTo(password);
        assertThat(adminUser.getName()).isEqualTo(name);
        assertThat(adminUser.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

}