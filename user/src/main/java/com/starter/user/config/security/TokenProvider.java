package com.starter.user.config.security;

import com.starter.user.config.payload.exception.ExpiredTokenException;
import com.starter.user.config.payload.exception.UnauthorizedTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
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

    private final static String AUTHORITY_CLAIMS_KEY = "authorities";

    private String issueToken(String id, String name, Claims claims, String secretKey, long expiredTime) {
        System.out.println("issueToken : " + id);
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
        String authoritiesClaims = authorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));
        claims.put(AUTHORITY_CLAIMS_KEY, authoritiesClaims);
        return issueToken(id, name, Jwts.claims(claims), accessTokenSecretKey, accessTokenExpiredTime);
    }

    public String createRefreshToken(String id, String name, Set<GrantedAuthority> authorities) {
        Map<String, Object> claims = new HashMap<>();
        String authoritiesClaims = authorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));
        claims.put(AUTHORITY_CLAIMS_KEY, authoritiesClaims);
        return issueToken(id, name, Jwts.claims(claims), refreshTokenSecretKey, refreshTokenExpiredTime);
    }

    private Boolean validateToken(String token, String secretKey) {
        try {
            getClaim(token, secretKey);
            return true;
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (Exception e) {
            throw new UnauthorizedTokenException();
        }
    }

    public Boolean validateAccessToken(String token) {
        return validateToken(token, accessTokenSecretKey);
    }

    public Boolean validateRefreshToken(String token) {
        return validateToken(token, refreshTokenSecretKey);
    }

    private Claims getClaim(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }


    public Long getAccessTokenId(String token) {
        System.out.println("token : " + token);
        System.out.println("claim : " + getClaim(token, accessTokenSecretKey));
        return Optional.ofNullable(getClaim(token, accessTokenSecretKey).getId())
            .map(Long::parseLong)
            .orElse(null);
    }

    public Long getRefreshTokenId(String token) {
        return Optional.ofNullable(getClaim(token, refreshTokenSecretKey).getId())
            .map(Long::parseLong)
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
        final String authorities = String.valueOf(getClaim(token, accessTokenSecretKey).get(AUTHORITY_CLAIMS_KEY));
        return Arrays.stream(authorities.split(","))
            .map(auth -> (GrantedAuthority) () -> auth)
            .collect(Collectors.toList());
    }

    public Collection<? extends GrantedAuthority> getRefreshTokenAuthorities(String token) {
        final String authorities = String.valueOf(getClaim(token, refreshTokenSecretKey).get(AUTHORITY_CLAIMS_KEY));
        return Arrays.stream(authorities.split(","))
            .map(auth -> (GrantedAuthority) () -> auth)
            .collect(Collectors.toList());
    }

}
