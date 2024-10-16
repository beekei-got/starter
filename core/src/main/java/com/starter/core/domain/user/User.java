package com.starter.core.domain.user;

import com.starter.core.domain.EntityBase;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Comment("회원")
@Getter
@Entity
@Table(name = "\"user\"", indexes = {
    @Index(name="IDX_user_user_type", columnList = "user_type"),
    @Index(name="IDX_user_user_status", columnList = "user_status"),
})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type", length = 50, discriminatorType = DiscriminatorType.STRING)
public abstract class User extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    @Column(name = "id")
    protected Long id;

    @Enumerated(EnumType.STRING)
    @Comment("회원 유형")
    @Column(name = "user_type", length = 50, nullable = false, insertable = false, updatable = false)
    protected UserType userType;

    @Enumerated(EnumType.STRING)
    @Comment("회원 상태")
    @Column(name = "user_status", length = 50, nullable = false)
    protected UserStatus userStatus;

    @Comment("성명")
    @Column(name = "user_name", length = 100, nullable = false)
    protected String userName;

    protected User(UserType userType, String userName) {
        this.userType = userType;
        this.userStatus = UserStatus.ACTIVE;
        this.userName = userName;
    }

    public void resign() {
        this.userStatus = UserStatus.RESIGN;
    }

    public void block() {
        this.userStatus = UserStatus.BLOCK;
    }

}
