package com.starter.core.domain.user.client.service.dto;

import com.starter.core.domain.user.Gender;
import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@Getter
@Builder
public class SaveClientUserParameterDTO {
	private final String email;
	private final String userName;
	private final String nickname;
	@Nullable
	private final Gender userGender;
	@Nullable
	private final LocalDate userBirthday;
	@Nullable
	private final String phoneNumber;
}
