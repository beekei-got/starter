package com.starter.user.config.security;

import com.starter.user.config.security.oauth2.filter.CustomOAuth2AccessTokenResponseClient;
import com.starter.user.config.security.oauth2.filter.CustomAuthorizationRequestResolver;
import com.starter.user.config.security.oauth2.handler.CustomAuthenticationFailureHandler;
import com.starter.user.config.security.oauth2.handler.CustomAuthenticationSuccessHandler;
import com.starter.user.config.security.oauth2.service.CustomOAuth2UserService;
import com.starter.user.domain.auth.service.AuthTokenDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final TokenProvider tokenProvider;
    private final AuthTokenDomainService authTokenDomainService;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
            .ignoring().requestMatchers(
            "classpath:/static/**",
            "classpath:/resources/**",
            "classpath:/META-INF/**",
            "/resources/**",
            "/favicon.ico",
            "/BOOT-INF/**",
            "/api/docs",
            "/api/docs/**",
            "/api/swagger-ui/**",
            "/user/login/**"
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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
            .cors(c -> c.configurationSource(corsConfigurationSource()))
            .headers(hc -> hc.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
            .authorizeHttpRequests(ar -> ar.anyRequest().permitAll())
            .oauth2Login(oauth2Login -> oauth2Login
                .authorizationEndpoint(config ->
                    config.authorizationRequestResolver(
                        new CustomAuthorizationRequestResolver(this.clientRegistrationRepository)))
                .tokenEndpoint(config ->
                    config.accessTokenResponseClient(new CustomOAuth2AccessTokenResponseClient()))
                .successHandler(new CustomAuthenticationSuccessHandler(this.tokenProvider, this.authTokenDomainService))
                .failureHandler(new CustomAuthenticationFailureHandler())
                .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(this.customOAuth2UserService)))
            .build();
    }

}
