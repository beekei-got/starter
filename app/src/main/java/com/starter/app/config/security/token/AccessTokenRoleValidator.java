package com.starter.app.config.security.token;

import com.starter.app.config.security.SecurityUtils;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class AccessTokenRoleValidator implements AuthorizationManager<MethodInvocation> {

	@Override
	public AuthorizationDecision check(Supplier<Authentication> authentication, MethodInvocation invocation) {
		TokenRole[] tokenRoles = invocation.getMethod().getAnnotation(AccessTokenRole.class).value();
		if (tokenRoles.length > 0) {
			Set<GrantedAuthority> tokenRoleAuthorities = Arrays.stream(tokenRoles)
				.map(TokenRole::getAuthority)
				.collect(Collectors.toSet());
			return new AuthorizationDecision(SecurityUtils.getAuthorities().stream().anyMatch(tokenRoleAuthorities::contains));
		}
		return new AuthorizationDecision(false);
	}

}
