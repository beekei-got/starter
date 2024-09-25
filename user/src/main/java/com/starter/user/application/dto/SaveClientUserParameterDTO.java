package com.starter.user.application.dto;

import com.starter.user.domain.user.Gender;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class SaveClientUserParameterDTO {
    private final String email;
    private final String userName;
    private final String nickname;
    private final Gender userGender;
    private final LocalDate userBirthday;
    private final String phoneNumber;
}
