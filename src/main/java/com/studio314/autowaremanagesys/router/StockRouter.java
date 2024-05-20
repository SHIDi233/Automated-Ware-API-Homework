package com.studio314.autowaremanagesys.router;


import com.studio314.autowaremanagesys.handler.StockHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class StockRouter {

    @Bean
    public RouterFunction<ServerResponse> stockRoute(StockHandler stockHandler) {
        return RouterFunctions.route()
                .path("/v2/wares/{wID}/stock", builder -> builder
                        .POST(accept(MediaType.APPLICATION_JSON), stockHandler::create)
                        .GET(accept(MediaType.APPLICATION_JSON), stockHandler::query)
                        .PUT("/{id}", accept(MediaType.APPLICATION_JSON), stockHandler::out)
                )
                .build();
    }

}
