package com.starter.app.config.security.token;

import com.starter.core.config.exception.ExpiredTokenException;
import com.starter.core.config.exception.UnauthorizedTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.security.jwt.access-token.secret-key}")
    private String accessTokenSecretKey;

    @Value("${spring.security.jwt.access-token.expired-time}")
    private long accessTokenExpiredTime;

    @Value("${spring.security.jwt.refresh-token.secret-key}")
    private String refreshTokenSecretKey;

    @Value("${spring.security.jwt.refresh-token.expired-time}")
    private long refreshTokenExpiredTime;

    private final static String USER_ID_CLAIM_KEY = "user_id";
    private final static String USER_NAME_CLAIM_KEY = "user_name";
    private final static String ROLES_CLAIM_KEY = "roles";
    private final static String ROLES_CLAIM_DELIMITER = ",";

    private String issueToken(UUID id, SubjectType subjectType, Claims claims, String secretKey, long expiredTime) {
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .setClaims(claims)
            .setId(id.toString())
            .setSubject(subjectType.toString())
            .setIssuer(applicationName)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expiredTime))
            .compact();
    }

    private String createRolesClaims(Set<GrantedAuthority> authorities) {
        return authorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(ROLES_CLAIM_DELIMITER));
    }

    public String issueAccessToken(UUID id, SubjectType subjectType,
                                   long userId, String userName, Set<GrantedAuthority> authorities) {
        Map<String, Object> claims = new HashMap<>() {{
            put(USER_ID_CLAIM_KEY, userId);
            put(USER_NAME_CLAIM_KEY, userName);
            put(ROLES_CLAIM_KEY, createRolesClaims(authorities));
        }};
        return issueToken(id, subjectType, Jwts.claims(claims), accessTokenSecretKey, accessTokenExpiredTime);
    }

    public String issueRefreshToken(UUID id, SubjectType subjectType,
                                    long userId, String userName, Set<GrantedAuthority> authorities) {
        Map<String, Object> claims = new HashMap<>() {{
            put(USER_ID_CLAIM_KEY, userId);
            put(USER_NAME_CLAIM_KEY, userName);
            put(ROLES_CLAIM_KEY, createRolesClaims(authorities));
        }};
        return issueToken(id, subjectType, Jwts.claims(claims), refreshTokenSecretKey, refreshTokenExpiredTime);
    }

    private Boolean validateToken(String token, String secretKey) throws ExpiredTokenException, UnauthorizedTokenException {
        try {
            getClaim(token, secretKey);
            return true;
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (Exception e) {
            throw new UnauthorizedTokenException();
        }
    }

    public Boolean validateAccessToken(String token) throws ExpiredTokenException, UnauthorizedTokenException {
        return validateToken(token, accessTokenSecretKey);
    }

    public Boolean validateRefreshToken(String token) throws ExpiredTokenException, UnauthorizedTokenException {
        return validateToken(token, refreshTokenSecretKey);
    }

    private Claims getClaim(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public Long getAccessTokenUserId(String token) {
        return Optional.ofNullable(getClaim(token, accessTokenSecretKey).get(USER_ID_CLAIM_KEY))
            .map(userId -> Long.valueOf(String.valueOf(userId)))
            .orElse(null);
    }

    public Long getRefreshTokenId(String token) {
        return Optional.ofNullable(getClaim(token, refreshTokenSecretKey).get(USER_ID_CLAIM_KEY))
            .map(userId -> Long.valueOf(String.valueOf(userId)))
            .orElse(null);
    }

    public SubjectType getAccessTokenType(String token) {
        return Optional.ofNullable(getClaim(token, accessTokenSecretKey).getSubject())
            .map(SubjectType::valueOf)
            .orElse(null);
    }

    public SubjectType getRefreshTokenType(String token) {
        return Optional.ofNullable(getClaim(token, refreshTokenSecretKey).getSubject())
            .map(SubjectType::valueOf)
            .orElse(null);
    }

    public LocalDateTime getAccessTokenExpiredDatetime(String token) {
        final Date expiredDate = getClaim(token, accessTokenSecretKey).getExpiration();
        return new java.sql.Timestamp(expiredDate.getTime()).toLocalDateTime();
    }

    public LocalDateTime getRefreshTokenExpiredDatetime(String token) {
        final Date expiredDate = getClaim(token, refreshTokenSecretKey).getExpiration();
        return new java.sql.Timestamp(expiredDate.getTime()).toLocalDateTime();
    }

    public Collection<? extends GrantedAuthority> getAccessTokenAuthorities(String token) {
        final String authorities = String.valueOf(getClaim(token, accessTokenSecretKey).get(ROLES_CLAIM_KEY));
        return Arrays.stream(authorities.split(ROLES_CLAIM_DELIMITER))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    }

    public Collection<? extends GrantedAuthority> getRefreshTokenAuthorities(String token) {
        final String authorities = String.valueOf(getClaim(token, refreshTokenSecretKey).get(ROLES_CLAIM_KEY));
        return Arrays.stream(authorities.split(ROLES_CLAIM_DELIMITER))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    }

    @Getter
    @AllArgsConstructor
    public enum SubjectType {

        GUEST_TOKEN("게스트 토큰"),
        CLIENT_USER_TOKEN("사용자 회원 토큰"),
        ADMIN_USER_TOKEN("관리자 회원 토큰");

        private final String name;

    }

}
