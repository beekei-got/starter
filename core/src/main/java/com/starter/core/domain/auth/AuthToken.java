package com.starter.core.domain.auth;

import com.starter.core.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Comment("인증토큰")
@Getter
@Entity
@Table(name = "auto_token")
@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthToken extends BaseEntity {

    @Comment("고유번호")
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", length = 16)
    protected UUID id;

    @Comment("회원 고유번호")
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;

    @Comment("AccessToken")
    @Column(name = "access_token", length = 500, nullable = false, updatable = false)
    private String accessToken;

    @Comment("AccessToken 만료일자")
    @Column(name = "access_token_expired_datetime", nullable = false, updatable = false)
    private LocalDateTime accessTokenExpiredDatetime;

    @Comment("RefreshToken")
    @Column(name = "refresh_token", length = 500, nullable = false, updatable = false)
    private String refreshToken;

    @Comment("RefreshToken 만료일자")
    @Column(name = "refresh_token_expired_datetime", nullable = false, updatable = false)
    private LocalDateTime refreshTokenExpiredDatetime;

    @Comment("발급 만료일자")
    @Column(name = "issued_expired_datetime", nullable = false, updatable = false)
    private LocalDateTime issueExpiredDatetime;

    @Comment("발급여부")
    @Column(name = "is_issued", columnDefinition = "TINYINT(1) DEFAULT 0", nullable = false, insertable = false)
    private boolean isIssued;

    @Comment("발급일자")
    @Column(name = "issued_datetime", insertable = false)
    private LocalDateTime issuedDatetime;

    public static AuthToken createToken(long userId,
                                        String accessToken, LocalDateTime accessTokenExpiredDatetime,
                                        String refreshToken, LocalDateTime refreshTokenExpiredDatetime,
                                        int issueExpiredMinute) {
        return  AuthToken.builder()
            .userId(userId)
            .accessToken(accessToken)
            .accessTokenExpiredDatetime(accessTokenExpiredDatetime)
            .refreshToken(refreshToken)
            .refreshTokenExpiredDatetime(refreshTokenExpiredDatetime)
            .issueExpiredDatetime(LocalDateTime.now().plusMinutes(issueExpiredMinute))
            .build();
    }

    public boolean isExpired() {
        return this.issueExpiredDatetime.isBefore(LocalDateTime.now());
    }

    public void issue() {
        this.isIssued = true;
        this.issuedDatetime = LocalDateTime.now();
    }

}
