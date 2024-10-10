package com.starter.app.application.impl;

import com.beekei.library.querydslBuilder.repostiory.QuerydslBuilder;
import com.starter.app.application.ClientUserInfoServiceV1;
import com.starter.core.config.exception.ExceptionMessageType;
import com.starter.core.config.exception.NotExistDataException;
import com.starter.core.domain.user.client.ClientUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.starter.core.domain.user.client.QClientUser.clientUser;

@Component
@RequiredArgsConstructor
public class ClientUserInfoServiceV1Impl implements ClientUserInfoServiceV1 {

	private final QuerydslBuilder querydslBuilder;

	@Override
	public void getClientUserInfo(long userId) {
		ClientUser user = querydslBuilder
			.selectFrom(clientUser)
			.where(userId, clientUser.id)
			.getRow()
			.orElseThrow(() -> new NotExistDataException(ExceptionMessageType.NOT_EXIST_USER));
	}

}
