package com.starter.user.application.dto;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.starter.user.config.querydsl.QuerydslSelectDTO;
import com.starter.user.domain.user.Gender;
import com.starter.user.domain.user.UserStatus;
import com.starter.user.domain.user.client.ClientUser;
import lombok.*;

import java.time.LocalDate;

import static com.starter.user.domain.user.client.QClientUser.clientUser;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ClientUserInfoDTO {

    private final long userId;
    private final UserStatus userStatus;
    private final String userEmail;
    private final String userName;
    private final String nickname;
    private final Gender userGender;
    private final LocalDate userBirthday;
    private final String phoneNumber;

    public static ClientUserInfoDTO of(ClientUser clientUser) {
        return ClientUserInfoDTO.builder()
            .userId(clientUser.getId())
            .userStatus(clientUser.getStatus())
            .userEmail(clientUser.getEmail())
            .userName(clientUser.getName())
            .nickname(clientUser.getNickname())
            .userGender(clientUser.getGender())
            .userBirthday(clientUser.getBirthday())
            .phoneNumber(clientUser.getPhoneNumber())
            .build();
    }

}
