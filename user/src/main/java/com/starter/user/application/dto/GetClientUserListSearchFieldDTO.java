package com.starter.user.application.dto;

import com.starter.user.domain.user.Gender;
import com.starter.user.domain.user.UserStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class GetClientUserListSearchFieldDTO {
    private Set<UserStatus> userStatusIn;
    private String userEmail;
    private String userName;
    private String nickname;
    private Set<Gender> userGenderIn;
    private LocalDate startUserBirthday;
    private LocalDate endUserBirthday;
    private String phoneNumber;
}
