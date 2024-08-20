package com.starter.app.config.aouth2;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

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

}
