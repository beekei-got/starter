package com.starter.app.config.security;

import com.starter.app.config.security.aouth2.handler.CustomAuthenticationFailureHandler;
import com.starter.app.config.security.aouth2.handler.CustomAuthenticationSuccessHandler;
import com.starter.app.config.security.aouth2.service.CustomOAuth2UserService;
import com.starter.core.domain.auth.AuthTokenDomainService;
import com.starter.core.domain.auth.AuthTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

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
            "/api/docs",
            "/api/docs/**",
            "/api/swagger-ui/**",
            "/BOOT-INF/**"
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
        http.csrf(AbstractHttpConfigurer::disable)
            .cors(c -> c.configurationSource(corsConfigurationSource()))
            .headers(hc -> hc.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
            .authorizeHttpRequests(ar -> ar
//                .requestMatchers("/token").permitAll()
                .anyRequest().permitAll())
            .oauth2Login(oauth2Login -> oauth2Login
                .successHandler(new CustomAuthenticationSuccessHandler(tokenProvider, authTokenDomainService))
                .failureHandler(new CustomAuthenticationFailureHandler())
                .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(customOAuth2UserService)));
        return http.build();
    }

}
