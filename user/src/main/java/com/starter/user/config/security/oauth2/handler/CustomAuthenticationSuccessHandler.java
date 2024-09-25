package com.starter.user.config.security.oauth2.handler;

import com.starter.user.config.security.TokenProvider;
import com.starter.user.config.security.oauth2.service.CustomOAuth2User;
import com.starter.user.domain.auth.service.AuthTokenDomainService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final AuthTokenDomainService authTokenDomainService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        System.out.println("customOAuth2User.getId() : " + customOAuth2User.getId());
        String accessToken = tokenProvider.createAccessToken(String.valueOf(customOAuth2User.getId()), customOAuth2User.getName(), customOAuth2User.getAuthorities());
        LocalDateTime accessTokenExpiredDatetime = tokenProvider.getAccessTokenExpiredDatetime(accessToken);
        String refreshToken = tokenProvider.createRefreshToken(String.valueOf(customOAuth2User.getId()), customOAuth2User.getName(), customOAuth2User.getAuthorities());
        LocalDateTime refreshTokenExpiredDatetime = tokenProvider.getRefreshTokenExpiredDatetime(refreshToken);
        UUID authTokenId = authTokenDomainService.saveAuthToken(
            customOAuth2User.getId(),
            accessToken, accessTokenExpiredDatetime,
            refreshToken, refreshTokenExpiredDatetime);

        String appRedirectUri = (String) customOAuth2User.getAdditionalParameters().get("appRedirectUri");
        response.sendRedirect(appRedirectUri + "?authTokenId=" + authTokenId);
    }


}
