package com.starter.core.domain.user.business;

import com.starter.core.domain.user.UserStatus;
import com.starter.core.domain.user.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BusinessUserTest {

	@Test
	@DisplayName("사업자 회원 생성")
	void createUser() {
		String loginId = "admin123";
		String password = "11111";
		String name = "홍길동";
		String phoneNumber = "01012341234";
		BusinessUser businessUser = BusinessUser.createUser(loginId, password, name, phoneNumber);

		assertThat(businessUser.getUserType()).isEqualTo(UserType.BUSINESS);
		assertThat(businessUser.getUserStatus()).isEqualTo(UserStatus.ACTIVE);
		assertThat(businessUser.getLoginId()).isEqualTo(loginId);
		assertThat(businessUser.getPassword()).isEqualTo(password);
		assertThat(businessUser.getUserName()).isEqualTo(name);
		assertThat(businessUser.getPhoneNumber()).isEqualTo(phoneNumber);
	}



}