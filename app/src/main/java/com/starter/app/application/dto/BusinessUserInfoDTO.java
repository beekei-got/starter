package com.starter.app.application.dto;

import com.beekei.library.querydslBuilder.QuerydslSelectDTO;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.starter.core.domain.user.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.starter.core.domain.user.business.QBusinessUser.businessUser;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessUserInfoDTO implements QuerydslSelectDTO {

	private long userId;
	private UserStatus userStatus;
	private String userName;
	private String loginId;
	private String phoneNumber;

	@Override
	public ConstructorExpression<?> constructor() {
		return Projections.constructor(BusinessUserInfoDTO.class,
			businessUser.id,
			businessUser.userStatus,
			businessUser.userName,
			businessUser.loginId,
			businessUser.phoneNumber);
	}

}
