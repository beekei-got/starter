package com.starter.app.application;

import org.springframework.stereotype.Service;

@Service
public interface ClientUserInfoServiceV1 {
	void getClientUserInfo(long userId);
}
