package com.starter.core.domain.user;

import com.starter.core.domain.user.ClientUser;
import com.starter.core.domain.user.Gender;
import com.starter.core.domain.user.UserStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


class ClientUserTest {

    @Test
    void 사용자_회원_생성() {
        String email = "test@test.com";
        String name = "홍길동";
        String nickname = "테스터";
        Gender gender = Gender.MALE;
        LocalDate birthday = LocalDate.of(1993, 10, 20);
        String phoneNumber = "01011112222";
        ClientUser clientUser = ClientUser.createClientUser(email, name, nickname, gender, birthday, phoneNumber);

        assertThat(clientUser.getEmail()).isEqualTo(email);
        assertThat(clientUser.getName()).isEqualTo(name);
        assertThat(clientUser.getNickname()).isEqualTo(nickname);
        assertThat(clientUser.getGender()).isEqualTo(gender);
        assertThat(clientUser.getBirthday()).isEqualTo(birthday);
        assertThat(clientUser.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(clientUser.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }



}