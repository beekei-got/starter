package com.starter.user.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;

import java.util.List;
import java.util.Set;

public class TokenWhiteList {

    private static final List<Path> WHITE_LIST = List.of(
        new Path("/user/client/sign/in", HttpMethod.GET), // 사용자 회원 로그인 페이지
        new Path("/api/v*/token/auth", HttpMethod.POST) // 인증 토큰 발급
    );

    public static String[] getWhitelistByMethod(HttpMethod method) {
        return WHITE_LIST.stream()
            .filter(white -> white.getMethods().contains(method))
            .map(Path::getUri)
            .toArray(String[]::new);
    }

    public static boolean isWhitelist(String contextPath, String currentPath, HttpMethod method) {
        AntPathMatcher matcher = new AntPathMatcher();
        return WHITE_LIST.stream()
            .filter(path -> matcher.match(contextPath + path.getUri(), currentPath))
            .anyMatch(white -> white.getMethods().contains(method));
    }

    @Getter
    @AllArgsConstructor
    public static class Path {

        private final String uri;
        private final Set<HttpMethod> methods;

        public Path(String uri, HttpMethod method) {
            this.uri = uri;
            this.methods = Set.of(method);
        }
    }

}
