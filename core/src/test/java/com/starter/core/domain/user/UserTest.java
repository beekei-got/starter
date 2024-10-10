package com.starter.core.domain.user;

import com.starter.core.domain.user.UserStatus;
import com.starter.core.domain.user.client.ClientUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    @DisplayName("회원 탈퇴")
    void resign() {
        ClientUser clientUser = ClientUserMock.createUser();
        clientUser.resign();

        assertThat(clientUser.getStatus()).isEqualTo(UserStatus.RESIGN);
    }

    @Test
    @DisplayName("회원 차단")
    void block() {
        ClientUser clientUser = ClientUserMock.createUser();
        clientUser.block();

        assertThat(clientUser.getStatus()).isEqualTo(UserStatus.BLOCK);
    }

}