package com.studio314.autowaremanagesys.router;


import com.studio314.autowaremanagesys.handler.WareHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class WareRouter {

    @Bean
    public RouterFunction<ServerResponse> wareRoute(WareHandler wareHandler) {
        return RouterFunctions.route()
                .path("/v2/wares", builder -> builder
                        .POST(accept(MediaType.APPLICATION_JSON), wareHandler::create)
                        .GET(accept(MediaType.APPLICATION_JSON), wareHandler::query)
                        .DELETE("/{id}", accept(MediaType.APPLICATION_JSON), wareHandler::delete)
                )
                .build();
    }

}
