package com.starter.core.domain.user.business;

import com.starter.core.domain.user.User;
import com.starter.core.domain.user.UserType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Comment("사업자 회원")
@Getter
@Entity
@Table(name = "business_user", uniqueConstraints = {
	@UniqueConstraint(name = "UK_business_user_login_id", columnNames = { "login_id" })
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DiscriminatorValue("BUSINESS")
public class BusinessUser extends User {

	@Comment("아이디")
	@Column(name = "login_id", length = 150, nullable = false)
	private String loginId;

	@Comment("비밀번호")
	@Column(name = "password", length = 300, nullable = false)
	private String password;

	@Comment("이메일")
	@Column(name = "email", length = 150, nullable = false)
	private String email;

	@Comment("휴대폰번호")
	@Column(name = "phone_number", length = 20, nullable = false)
	private String phoneNumber;

	private BusinessUser(String loginId, String password, String name, String email, String phoneNumber) {
		super(UserType.BUSINESS, name);
		this.loginId = loginId;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public static BusinessUser createUser(String loginId, String password, String name, String email, String phoneNumber) {
		return new BusinessUser(loginId, password, name, email, phoneNumber);
	}

}
