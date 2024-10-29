package com.starter.core.domain.auth;

import com.starter.core.domain.EntityBase;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Comment("인증토큰")
@Getter
@Entity
@Table(name = "auto_token")
@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthToken extends EntityBase {

    @Comment("고유번호")
    @Id
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", length = 16)
    protected UUID id;

    @Comment("회원 고유번호")
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;

    @Comment("회원 성명")
    @Column(name = "user_name", length = 100, nullable = false)
    protected String userName;

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

    @Comment("토큰 권한")
    @Column(name = "token_authority", length = 200, nullable = false, updatable = false)
    @Convert(converter = AuthTokenAuthoritiesConverter.class)
    private Set<String> authorities;

    @Comment("발급 만료일자")
    @Column(name = "issued_expired_datetime", nullable = false, updatable = false)
    private LocalDateTime issueExpiredDatetime;

    @Comment("발급여부")
    @Column(name = "is_issued", columnDefinition = "TINYINT(1) DEFAULT 0", nullable = false, insertable = false)
    private boolean isIssued;

    @Comment("발급일자")
    @Column(name = "issued_datetime", insertable = false)
    private LocalDateTime issuedDatetime;

    public static AuthToken createAuthToken(UUID authTokenId, long userId, String userName,
                                            String accessToken, LocalDateTime accessTokenExpiredDatetime,
                                            String refreshToken, LocalDateTime refreshTokenExpiredDatetime,
                                            Set<String> authorities,
                                            int issuedExpiredMinute) {
        return  AuthToken.builder()
            .id(authTokenId)
            .userId(userId)
            .userName(userName)
            .accessToken(accessToken)
            .accessTokenExpiredDatetime(accessTokenExpiredDatetime)
            .refreshToken(refreshToken)
            .refreshTokenExpiredDatetime(refreshTokenExpiredDatetime)
            .authorities(authorities)
            .issueExpiredDatetime(LocalDateTime.now().plusMinutes(issuedExpiredMinute))
            .build();
    }

    public static AuthToken createIssuedAuthToken(UUID authTokenId, long userId, String userName,
                                                  String accessToken, LocalDateTime accessTokenExpiredDatetime,
                                                  String refreshToken, LocalDateTime refreshTokenExpiredDatetime,
                                                  Set<String> authorities) {
        return  AuthToken.builder()
            .id(authTokenId)
            .userId(userId)
            .userName(userName)
            .accessToken(accessToken)
            .accessTokenExpiredDatetime(accessTokenExpiredDatetime)
            .refreshToken(refreshToken)
            .refreshTokenExpiredDatetime(refreshTokenExpiredDatetime)
            .authorities(authorities)
            .issueExpiredDatetime(LocalDateTime.now())
            .isIssued(true)
            .build();
    }

    public boolean isIssueExpired() {
        return this.issueExpiredDatetime.isBefore(LocalDateTime.now());
    }

    public boolean isAccessTokenExpired() {
        return this.accessTokenExpiredDatetime.isBefore(LocalDateTime.now());
    }

    public boolean isRefreshTokenExpired() {
        return this.refreshTokenExpiredDatetime.isBefore(LocalDateTime.now());
    }

    public void issue() {
        this.isIssued = true;
        this.issuedDatetime = LocalDateTime.now();
    }

}
