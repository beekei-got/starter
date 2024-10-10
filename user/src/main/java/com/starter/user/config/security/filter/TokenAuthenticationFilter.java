package com.starter.user.config.security.filter;

import com.starter.user.config.payload.exception.ApiException;
import com.starter.user.config.payload.exception.TokenException;
import com.starter.user.config.payload.exception.UnauthorizedTokenException;
import com.starter.user.config.security.TokenProvider;
import com.starter.user.config.security.TokenWhiteList;
import com.starter.user.domain.user.admin.service.AdminUserDomainService;
import com.starter.user.domain.user.client.service.ClientUserDomainService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final static String TOKEN_HEADER_NAME = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    private final TokenProvider tokenProvider;
    private final ClientUserDomainService clientUserDomainService;
    private final AdminUserDomainService adminUserDomainService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String requestURI = request.getRequestURI();
        HttpMethod method = HttpMethod.valueOf(request.getMethod());
        return TokenWhiteList.isWhitelist(contextPath, requestURI, method);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String accessToken = request.getHeader(TOKEN_HEADER_NAME);
            if (!this.shouldNotFilter(request)) {
                if (StringUtils.isBlank(accessToken)) throw new UnauthorizedTokenException();

                String prefix = accessToken.trim().substring(0, TOKEN_PREFIX.length()).toUpperCase();
                if (!TOKEN_PREFIX.equalsIgnoreCase(prefix)) throw new UnauthorizedTokenException();

                final String token = accessToken.trim().substring(TOKEN_PREFIX.length());
                if (tokenProvider.validateAccessToken(token)) {
                    long userId = tokenProvider.getAccessTokenUserId(token);
                    TokenProvider.SubjectType subjectType = tokenProvider.getAccessTokenType(token);
                    Collection<? extends GrantedAuthority> authority = tokenProvider.getAccessTokenAuthorities(token);

                    if (subjectType.equals(TokenProvider.SubjectType.CLIENT_USER_TOKEN)) {
                        clientUserDomainService.checkAccessClientUser(userId);
                    } else if (subjectType.equals(TokenProvider.SubjectType.ADMIN_USER_TOKEN)) {
                        adminUserDomainService.checkAccessAdminUser(userId);
                    }

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, null, authority);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (ApiException e) {
            request.setAttribute("ExceptionType", e.getApiResponseType().toString());
            throw e;
        } catch (TokenException e) {
            request.setAttribute("ExceptionType", e.getApiResponseType().toString());
            throw e;
        }
    }

}