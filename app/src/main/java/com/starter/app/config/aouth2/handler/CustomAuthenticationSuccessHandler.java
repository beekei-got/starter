package com.starter.app.config.aouth2.handler;

import com.starter.app.config.aouth2.TokenProvider;
import com.starter.app.config.aouth2.service.CustomOAuth2User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String accessToken = tokenProvider.createAccessToken(String.valueOf(customOAuth2User.getId()), customOAuth2User.getName(), customOAuth2User.getAuthorities());
        String refreshToken = tokenProvider.createRefreshToken(String.valueOf(customOAuth2User.getId()), customOAuth2User.getName(), customOAuth2User.getAuthorities());
        response.addHeader("accessToken", accessToken);
        response.addHeader("refreshToken", refreshToken);
        response.sendRedirect("/");
    }

}
