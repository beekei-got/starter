package com.starter.app.config.security;

import com.starter.app.application.AuthTokenServiceV1;
import com.starter.app.config.security.filter.TokenAuthenticationFilter;
import com.starter.app.config.security.handler.TokenAccessDeniedHandler;
import com.starter.app.config.security.handler.TokenUnauthorizedHandler;
import com.starter.app.config.security.oauth2.filter.CustomOAuth2AccessTokenResponseClient;
import com.starter.app.config.security.oauth2.filter.CustomAuthorizationRequestResolver;
import com.starter.app.config.security.oauth2.handler.CustomAuthenticationFailureHandler;
import com.starter.app.config.security.oauth2.handler.CustomAuthenticationSuccessHandler;
import com.starter.app.config.security.oauth2.service.CustomOAuth2UserService;
import com.starter.app.config.security.token.AccessTokenRoleValidator;
import com.starter.app.config.security.token.TokenProvider;
import com.starter.core.domain.auth.service.AuthTokenDomainService;
import com.starter.core.domain.user.client.service.ClientUserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.Advisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final AuthTokenServiceV1 authTokenServiceV1;
    private final TokenProvider tokenProvider;
    private final ClientUserDomainService clientUserDomainService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
            .ignoring().requestMatchers(
            "classpath:/static/**",
            "classpath:/resources/**",
            "classpath:/META-INF/**",
            "/static/**",
            "/resources/**",
            "/WEB-INF/**",
            "/BOOT-INF/**",
            "/favicon.ico",
            "/api/docs",
            "/api/docs/**",
            "/api/swagger-ui/**"
        );
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public Advisor preAuthorize(AccessTokenRoleValidator accessTokenRoleValidator) {
        return AuthorizationManagerBeforeMethodInterceptor.preAuthorize(accessTokenRoleValidator);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
            .cors(c -> c.configurationSource(corsConfigurationSource()))
            .headers(hc -> hc.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
            .oauth2Login(oauth2Login -> oauth2Login
                .authorizationEndpoint(config ->
                    config.authorizationRequestResolver(
                        new CustomAuthorizationRequestResolver(this.clientRegistrationRepository)))
                .tokenEndpoint(config ->
                    config.accessTokenResponseClient(new CustomOAuth2AccessTokenResponseClient()))
                .successHandler(new CustomAuthenticationSuccessHandler(this.authTokenServiceV1))
                .failureHandler(new CustomAuthenticationFailureHandler())
                .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(this.customOAuth2UserService)))
            .authorizeHttpRequests(ar -> ar
                .requestMatchers(HttpMethod.GET, SecurityWhiteList.getWhitelistByMethod(HttpMethod.GET)).permitAll()
                .requestMatchers(HttpMethod.POST, SecurityWhiteList.getWhitelistByMethod(HttpMethod.POST)).permitAll()
                .requestMatchers(HttpMethod.PUT, SecurityWhiteList.getWhitelistByMethod(HttpMethod.PUT)).permitAll()
                .requestMatchers(HttpMethod.DELETE, SecurityWhiteList.getWhitelistByMethod(HttpMethod.DELETE)).permitAll()
                .anyRequest().authenticated())
            .exceptionHandling(handling -> handling
                .authenticationEntryPoint(new TokenUnauthorizedHandler())
                .accessDeniedHandler(new TokenAccessDeniedHandler()))
            .addFilterBefore(
                new TokenAuthenticationFilter(this.tokenProvider, this.clientUserDomainService),
                UsernamePasswordAuthenticationFilter.class)
            .build();
    }

}
