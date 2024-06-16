package com.studio314.autowaremanagesys.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.rmi.AccessException;

@RestControllerAdvice
public class AuthError implements ServerAccessDeniedHandler {

    @Override
    @ExceptionHandler(AccessException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        System.out.println("Access Denied");
        return null;
    }
}
