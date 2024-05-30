package com.studio314.autowaremanagesys.handler;

import com.studio314.autowaremanagesys.service.OrderService;
import com.studio314.autowaremanagesys.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class OrderHandler {

    @Autowired
    OrderService orderService;

    /**
     * 查询所有订单
     */
    public Mono<ServerResponse> list(ServerRequest request) {
        return request.bodyToMono(HashMap.class)
                .flatMap(body -> {
                    int wID = Integer.parseInt(request.pathVariable("wID"));
                    return ok().contentType(MediaType.APPLICATION_JSON).bodyValue(orderService.listOrders(wID));
                });
    }

    /**
     * 查询某一订单
     */
    public Mono<ServerResponse> query(ServerRequest request) {
        return request.bodyToMono(HashMap.class)
                .flatMap(body -> {
                    String orderNumber = (String) body.get("order");
                    return ok().contentType(MediaType.APPLICATION_JSON).bodyValue(orderService.query(orderNumber));
                });
    }

    /**
     * 创建入库订单
     */
    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(HashMap.class)
                .flatMap(body -> {
                    int wID = Integer.parseInt(request.pathVariable("wID"));
                    int cargoID = (int)body.get("cargoID");
                    int stockNum = (int)body.get("stockNum");
                    return ok().contentType(MediaType.APPLICATION_JSON).bodyValue(orderService.createInOrder(wID, cargoID, stockNum));
                });
    }

    /**
     * 创建出库订单
     */
    public Mono<ServerResponse> out(ServerRequest request) {
        return request.bodyToMono(HashMap.class)
                .flatMap(body -> {
                    int wID = Integer.parseInt(request.pathVariable("wID"));
                    int cargoID = (int)body.get("cargoID");
                    int stockNum = (int)body.get("stockNum");
                    return ok().contentType(MediaType.APPLICATION_JSON).bodyValue(orderService.createOutOrder(wID, cargoID, stockNum));
                });
    }

}
