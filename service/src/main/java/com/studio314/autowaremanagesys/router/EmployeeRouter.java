package com.studio314.autowaremanagesys.router;

import com.studio314.autowaremanagesys.handler.EmployeeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class EmployeeRouter {

    @Bean
    public RouterFunction<ServerResponse> employeeRoute(EmployeeHandler employeeHandler) {
        return RouterFunctions.route()
                .path("/v2/wares/{wID}/auth", builder -> builder
                        .POST(accept(MediaType.APPLICATION_JSON), employeeHandler::addEmployee)
                        .GET(accept(MediaType.APPLICATION_JSON), employeeHandler::getEmployee)
                        .DELETE("/{uID}", accept(MediaType.APPLICATION_JSON), employeeHandler::deleteEmployee)
                )
                .build();
    }

}
