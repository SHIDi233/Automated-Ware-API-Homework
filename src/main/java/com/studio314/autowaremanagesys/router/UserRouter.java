package com.studio314.autowaremanagesys.router;


import com.studio314.autowaremanagesys.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> userRoute(UserHandler userHandler) {
        return RouterFunctions.route()
                .path("/v2/users", builder -> builder
                        .POST("/register", accept(MediaType.APPLICATION_JSON), userHandler::register)
                        .POST("/login", accept(MediaType.APPLICATION_JSON), userHandler::login)
                )
                .build();
    }

}
