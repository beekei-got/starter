package com.starter.app.config.security.filter;

import com.starter.core.config.exception.ApiException;
import com.starter.core.config.exception.TokenException;
import com.starter.core.config.exception.UnauthorizedTokenException;
import com.starter.app.config.security.token.TokenProvider;
import com.starter.app.config.security.SecurityWhiteList;
import com.starter.core.domain.user.client.service.ClientUserDomainService;
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
import org.springframework.security.core.parameters.P;
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

//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) {
//        String contextPath = request.getContextPath();
//        String requestURI = request.getRequestURI();
//        HttpMethod method = HttpMethod.valueOf(request.getMethod());
//        return SecurityWhiteList.isWhitelist(contextPath, requestURI, method);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String contextPath = request.getContextPath();
            String requestURI = request.getRequestURI();
            HttpMethod method = HttpMethod.valueOf(request.getMethod());
            if (!SecurityWhiteList.isWhitelist(contextPath, requestURI, method)) {
                final String accessToken = request.getHeader(TOKEN_HEADER_NAME);
                if (StringUtils.isBlank(accessToken)) throw new UnauthorizedTokenException();

                String prefix = accessToken.trim().substring(0, TOKEN_PREFIX.length()).toUpperCase();
                if (!TOKEN_PREFIX.equalsIgnoreCase(prefix)) throw new UnauthorizedTokenException();

                final String token = accessToken.trim().substring(TOKEN_PREFIX.length());
                if (tokenProvider.validateAccessToken(token)) {
                    long userId = tokenProvider.getAccessTokenUserId(token);
                    Collection<? extends GrantedAuthority> authority = tokenProvider.getAccessTokenAuthorities(token);

                    clientUserDomainService.checkAccessClientUser(userId);

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, null, authority);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } else {
                SecurityContextHolder.getContext().setAuthentication(null);
            }

            filterChain.doFilter(request, response);
        } catch (ApiException e) {
            request.setAttribute("ExceptionType", e.getExceptionType().toString());
            throw e;
        } catch (TokenException e) {
            request.setAttribute("ExceptionType", e.getExceptionType().toString());
            throw e;
        }
    }

}
