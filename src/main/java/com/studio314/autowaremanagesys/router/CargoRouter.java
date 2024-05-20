package com.studio314.autowaremanagesys.router;


import com.studio314.autowaremanagesys.handler.CargoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class CargoRouter {

    @Bean
    public RouterFunction<ServerResponse> cargoRoute(CargoHandler cargoHandler) {
        return RouterFunctions.route()
                .path("/v2/cargo", builder -> builder
                        .GET(accept(MediaType.APPLICATION_JSON), cargoHandler::queryAllCargos)
                        .GET("/{cargoID}", accept(MediaType.APPLICATION_JSON), cargoHandler::queryCargo)
                        .POST(accept(MediaType.APPLICATION_JSON), cargoHandler::addCargoRoot)
                        .POST("/{cargoID}", accept(MediaType.APPLICATION_JSON), cargoHandler::addCargo)
                        .PUT("/{cargoID}", accept(MediaType.APPLICATION_JSON), cargoHandler::updateCargo)
                        .DELETE("/{cargoID}", accept(MediaType.APPLICATION_JSON), cargoHandler::deleteCargo)
                )
                .build();
    }

}
