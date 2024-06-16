package com.studio314.autowaremanagesys.handler;

import com.studio314.autowaremanagesys.service.StockService;
import com.studio314.autowaremanagesys.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.studio314.autowaremanagesys.utils.Result;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.cache.annotation.CacheEvict;

@Component
public class StockHandler {

    @Autowired
    StockService stockService;

    @PreAuthorize("hasPermission(#request.pathVariable(\"wID\"),'controller') or hasPermission(#request.pathVariable(\"wID\"),'user')")
//    @Cacheable(cacheNames = "stock", key = "'stock:'+#wID")
    public Mono<ServerResponse> query(ServerRequest request) {
        int wID = Integer.parseInt(request.pathVariable("wID"));
        return ok().contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Result.success(stockService.queryAllStocks(wID)));
    }

    @PreAuthorize("hasPermission(#request.pathVariable(\"wID\"),'controller') or hasPermission(#request.pathVariable(\"wID\"),'user')")
//    @CacheEvict(cacheNames = "stock", key = "'stock:'+#wID")
    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(HashMap.class)
                .flatMap(body -> {
                    int wID = Integer.parseInt(request.pathVariable("wID"));
                    int cargoID = (int)body.get("cargoID");
                    int stockNum = (int)body.get("stockNum");
                    return ok().contentType(MediaType.APPLICATION_JSON).bodyValue(stockService.createStock(wID, cargoID, stockNum));
                });
    }


    @PreAuthorize("hasPermission(#request.pathVariable(\"wID\"),'controller') or hasPermission(#request.pathVariable(\"wID\"),'user')")
//    @CacheEvict(cacheNames = "stock", key = "'stock:'+#wID")
    public Mono<ServerResponse> out(ServerRequest request) {
        return request.bodyToMono(HashMap.class)
                .flatMap(body -> {
                    int wID = Integer.parseInt(request.pathVariable("wID"));
                    int cargoID = (int)body.get("cargoID");
                    int stockNum = (int)body.get("stockNum");
                    return ok().contentType(MediaType.APPLICATION_JSON).bodyValue(stockService.outStock(wID, cargoID, stockNum));
                });
    }

}
