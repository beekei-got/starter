package com.starter.app.application.dto;

import com.beekei.library.querydslBuilder.dto.QuerydslSelectDTO;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.starter.core.domain.user.Gender;
import com.starter.core.domain.user.UserStatus;
import lombok.*;

import java.time.LocalDate;

import static com.starter.core.domain.user.client.QClientUser.clientUser;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientUserInfoDTO implements QuerydslSelectDTO {

	private long userId;
	private UserStatus userStatus;
	private String userName;
	private String nickname;
	private String email;
	private Gender gender;
	private LocalDate birthday;
	private String phoneNumber;

	@Override
	public ConstructorExpression<?> constructor() {
		return Projections.constructor(ClientUserInfoDTO.class,
			clientUser.id,
			clientUser.userStatus,
			clientUser.userName,
			clientUser.nickname,
			clientUser.email,
			clientUser.gender,
			clientUser.birthday,
			clientUser.phoneNumber);
	}

}
