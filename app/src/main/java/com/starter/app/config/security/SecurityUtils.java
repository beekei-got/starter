package com.starter.app.config.security;

import com.starter.core.config.exception.UnauthorizedTokenException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class SecurityUtils {

	public static Optional<Long> getTokenUserIdOptional() {
		return Optional.ofNullable(SecurityContextHolder.getContext())
			.map(SecurityContext::getAuthentication)
			.map(Authentication::getPrincipal)
			.filter(principal -> !principal.toString().equals("anonymousUser"))
			.map(principal -> Long.parseLong(String.valueOf(principal)));
	}

	public static Collection<? extends GrantedAuthority> getAuthorities() {
		return Optional.ofNullable(SecurityContextHolder.getContext())
			.map(SecurityContext::getAuthentication)
			.map(Authentication::getAuthorities)
			.orElseGet(HashSet::new);
	}

	public static long getTokenUserId() {
		return SecurityUtils.getTokenUserIdOptional()
			.orElseThrow(UnauthorizedTokenException::new);
	}

}
