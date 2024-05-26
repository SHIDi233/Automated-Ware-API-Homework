package com.studio314.autowaremanagesys.config;

import io.netty.handler.codec.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;

public class DefaultAuthenticationManagerResolver implements AuthenticationManagerResolver<HttpRequest> {

    private final AuthenticationManager authenticationManager;

    public DefaultAuthenticationManagerResolver(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationManager resolve(HttpRequest context) {
        return authenticationManager;
    }
}
