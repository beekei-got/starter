package com.starter.user.config.security.oauth2.filter;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

import java.util.*;

public class CustomAuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {

    private final OAuth2AuthorizationRequestResolver defaultAuthorizationRequestResolver;

    public CustomAuthorizationRequestResolver(ClientRegistrationRepository clientRegistrationRepository) {
        this.defaultAuthorizationRequestResolver = new DefaultOAuth2AuthorizationRequestResolver(
                clientRegistrationRepository,
            "/oauth2/authorization"
        );
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
        OAuth2AuthorizationRequest authorizationRequest = this.defaultAuthorizationRequestResolver.resolve(request);
        Map<String, Object> addParams = createAddParams(request);
        return authorizationRequest != null ?
            customAuthorizationRequest(authorizationRequest, addParams) : null;
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request, String clientRegistrationId) {
        OAuth2AuthorizationRequest authorizationRequest = this.defaultAuthorizationRequestResolver.resolve(request, clientRegistrationId);
        Map<String, Object> addParams = createAddParams(request);
        return authorizationRequest != null ?
            customAuthorizationRequest(authorizationRequest, addParams) : null;
    }

    private Map<String, Object> createAddParams(HttpServletRequest request) {
        Set<String> params = Set.of(
            "appRedirectUri"
        );
        Map<String, Object> addParams = new HashMap<>();
        params.forEach(param -> Optional.ofNullable(request.getParameter(param))
            .ifPresent(value -> addParams.put(param, value)));
        return addParams;
    }

    private OAuth2AuthorizationRequest customAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest,
                                                                  Map<String, Object> addParams) {
        return OAuth2AuthorizationRequest.from(authorizationRequest)
            .additionalParameters(params -> params.putAll(addParams))
            .build();
    }

}
