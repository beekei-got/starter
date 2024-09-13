package com.starter.user.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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

    private final static String AUTHORITY_CLAIMS_KEY = "authorities";

    private String issueToken(String id, String name, Claims claims, String secretKey, long expiredTime) {
        return Jwts.builder()
            .setId(id)
            .setSubject(name)
            .setClaims(claims)
            .setIssuer(applicationName)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expiredTime))
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact();
    }

    public String createAccessToken(String id, String name, Set<GrantedAuthority> authorities) {
        Map<String, Object> claims = new HashMap<>();
        String authoritiesClaims = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        claims.put(AUTHORITY_CLAIMS_KEY, authoritiesClaims);
        return issueToken(id, name, Jwts.claims(claims), accessTokenSecretKey, accessTokenExpiredTime);
    }

    public String createRefreshToken(String id, String name, Set<GrantedAuthority> authorities) {
        Map<String, Object> claims = new HashMap<>();
        String authoritiesClaims = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        claims.put(AUTHORITY_CLAIMS_KEY, authoritiesClaims);
        return issueToken(id, name, Jwts.claims(claims), refreshTokenSecretKey, refreshTokenExpiredTime);
    }

    private Claims getClaim(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private Claims getAccessTokenClaim(String token) {
        return getClaim(token, accessTokenSecretKey);
    }

    private Claims getRefreshTokenClaim(String token) {
        return getClaim(token, refreshTokenSecretKey);
    }

    public LocalDateTime getAccessTokenExpiredDatetime(String token) {
        final Date expiredDate = getAccessTokenClaim(token).getExpiration();
        return new java.sql.Timestamp(expiredDate.getTime()).toLocalDateTime();
    }

    public LocalDateTime getRefreshTokenExpiredDatetime(String token) {
        final Date expiredDate = getRefreshTokenClaim(token).getExpiration();
        return new java.sql.Timestamp(expiredDate.getTime()).toLocalDateTime();
    }

}
