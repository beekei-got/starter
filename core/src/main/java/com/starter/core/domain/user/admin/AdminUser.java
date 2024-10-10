package com.starter.core.domain.user.admin;

import com.starter.core.domain.user.User;
import com.starter.core.domain.user.UserType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Comment("관리자 회원")
@Getter
@Entity
@Table(name = "admin_user", uniqueConstraints = {
    @UniqueConstraint(name = "UK_admin_user_login_id", columnNames = { "login_id" })
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DiscriminatorValue("ADMIN")
public class AdminUser extends User {

    @Comment("아이디")
    @Column(name = "login_id", length = 150, nullable = false)
    private String loginId;

    @Comment("비밀번호")
    @Column(name = "password", length = 300, nullable = false)
    private String password;

    private AdminUser(String loginId, String password, String name) {
        super(UserType.ADMIN, name);
        this.loginId = loginId;
        this.password = password;
    }

    public static AdminUser createUser(String loginId, String password, String name) {
        return new AdminUser(loginId, password, name);
    }

}
