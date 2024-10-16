package com.starter.app.application;

import com.starter.app.application.dto.ClientUserInfoDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface ClientUserInfoService {

	@Transactional(readOnly = true)
	ClientUserInfoDTO getClientUserInfo(long userId);

}
