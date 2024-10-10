package com.starter.core.domain.user;

import com.starter.user.domain.user.client.ClientUser;

import java.time.LocalDate;

public class ClientUserMock {

    public static ClientUser createUser() {
        ClientUser clientUser = ClientUser.createUser(
            "test@test.com",
            "테스터",
            "닉네임",
            Gender.MALE,
            LocalDate.of(1993, 10, 20),
            "01011112222");
        clientUser.id = 1L;
        return clientUser;
    }

    public static ClientUser createResignUser() {
        ClientUser clientUser = createUser();
        clientUser.resign();
        return clientUser;
    }

    public static ClientUser createBlockUser() {
        ClientUser clientUser = createUser();
        clientUser.block();
        return clientUser;
    }

}
