package com.starter.user.application.dto;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.starter.user.config.querydsl.QuerydslSelectDTO;
import com.starter.user.domain.user.Gender;
import com.starter.user.domain.user.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.starter.user.domain.user.client.QClientUser.clientUser;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientUserListDTO implements QuerydslSelectDTO {

    private long userId;
    private UserStatus userStatus;
    private String userEmail;
    private String userName;
    private String nickname;
    private Gender userGender;
    private LocalDate userBirthday;
    private String phoneNumber;

    @Override
    public ConstructorExpression<?> constructor() {
        return Projections.constructor(ClientUserListDTO.class,
            clientUser.id,
            clientUser.status,
            clientUser.email,
            clientUser.name,
            clientUser.nickname,
            clientUser.gender,
            clientUser.birthday,
            clientUser.phoneNumber
        );
    }

}
