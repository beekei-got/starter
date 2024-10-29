package com.starter.app.config.security.oauth2.handler;

import com.starter.app.application.AuthTokenServiceV1;
import com.starter.app.config.security.oauth2.service.CustomOAuth2User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final AuthTokenServiceV1 authTokenServiceV1;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        UUID authTokenId = authTokenServiceV1.saveAuthToken(customOAuth2User.getId(), customOAuth2User.getName(), customOAuth2User.getAuthorities());

        String appRedirectUri = (String) customOAuth2User.getAdditionalParameters().get("appRedirectUri");
        response.sendRedirect(appRedirectUri + "?authTokenId=" + authTokenId);
    }


}
