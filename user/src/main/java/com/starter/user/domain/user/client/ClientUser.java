package com.starter.user.domain.user.client;

import com.starter.user.domain.user.Gender;
import com.starter.user.domain.user.User;
import com.starter.user.domain.user.UserType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

@Comment("사용자 회원")
@Getter
@Entity
@Table(name = "client_user", uniqueConstraints = {
    @UniqueConstraint(name = "UK_client_user_email", columnNames = { "email" }),
    @UniqueConstraint(name = "UK_client_user_nickname", columnNames = { "nickname" }),
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DiscriminatorValue("CLIENT")
public class ClientUser extends User {

    @Comment("이메일")
    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @Comment("닉네임")
    @Column(name = "nickname", length = 100, nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Comment("성별")
    @Column(name = "gender", length = 50)
    private Gender gender;

    @Comment("생년월일")
    @Column(name = "birthday")
    private LocalDate birthday;

    @Comment("휴대폰번호")
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    private ClientUser(String email, String name, String nickname,
                       @Nullable Gender gender, @Nullable LocalDate birthday, @Nullable String phoneNumber) {
        super(UserType.CLIENT, name);
        this.email = email;
        this.nickname = nickname;
        this.gender = gender;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
    }

    public static ClientUser createClientUser(String email, String name, String nickname,
                                              @Nullable Gender gender, @Nullable LocalDate birthday, @Nullable String phoneNumber) {
        return new ClientUser(email, name, nickname, gender, birthday, phoneNumber);
    }

}
