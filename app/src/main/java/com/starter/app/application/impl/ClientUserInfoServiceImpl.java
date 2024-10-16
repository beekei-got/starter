package com.starter.app.application.impl;

import com.beekei.library.querydslBuilder.repostiory.QuerydslBuilder;
import com.starter.app.application.ClientUserInfoService;
import com.starter.app.application.dto.ClientUserInfoDTO;
import com.starter.core.config.exception.ExceptionMessageType;
import com.starter.core.config.exception.NotExistDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.starter.core.domain.user.client.QClientUser.clientUser;

@Component
@RequiredArgsConstructor
public class ClientUserInfoServiceImpl implements ClientUserInfoService {

	private final QuerydslBuilder querydslBuilder;

	@Override
	public ClientUserInfoDTO getClientUserInfo(long userId) {
		return querydslBuilder
			.select(ClientUserInfoDTO.class)
			.from(clientUser)
			.where(userId, clientUser.id)
			.getRow()
			.orElseThrow(() -> new NotExistDataException(ExceptionMessageType.NOT_EXIST_USER));
	}

}
