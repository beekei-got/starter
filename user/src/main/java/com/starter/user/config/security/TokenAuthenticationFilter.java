package com.starter.user.config.security;

import com.starter.user.config.payload.ApiResponseType;
import com.starter.user.config.payload.exception.ApiException;
import com.starter.user.config.payload.exception.ExpiredTokenException;
import com.starter.user.config.payload.exception.TokenException;
import com.starter.user.config.payload.exception.UnauthorizedTokenException;
import com.starter.user.config.security.oauth2.service.CustomOAuth2UserService;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final static String TOKEN_HEADER_NAME = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    private final TokenProvider tokenProvider;
    private final CustomOAuth2UserService customOAuth2UserService;

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
            if (StringUtils.isNotBlank(accessToken)) {
                String prefix = accessToken.trim().substring(0, TOKEN_PREFIX.length()).toUpperCase();
                if (!TOKEN_PREFIX.equalsIgnoreCase(prefix)) throw new RuntimeException("Header Prefix Token Type");

                final String token = accessToken.trim().substring(TOKEN_PREFIX.length());
                if (tokenProvider.validateAccessToken(token)) {
                    long userId = tokenProvider.getAccessTokenId(token);
                    Collection<? extends GrantedAuthority> authority = tokenProvider.getAccessTokenAuthorities(token);

                    customOAuth2UserService.checkAccessClientUser(userId);

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, null, authority);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (ApiException e) {
            request.setAttribute("ExceptionType", e.getApiResponseType().toString());
            throw e;
        } catch (TokenException e) {
            request.setAttribute("ExceptionType", e.getApiResponseType().toString());
            throw e;
        } finally {
            filterChain.doFilter(request, response);
        }
    }

}
