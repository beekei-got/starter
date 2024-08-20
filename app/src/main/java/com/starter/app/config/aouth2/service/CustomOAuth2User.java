package com.starter.app.config.aouth2.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@ToString
@Getter
@AllArgsConstructor
public class CustomOAuth2User implements OAuth2User, Serializable {

    private final long id;
    private final String email;
    private final Set<GrantedAuthority> authorities;
    private final String registrationId;
    private final Map<String, Object> attributes;

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return email;
    }

}
