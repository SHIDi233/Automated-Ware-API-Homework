package com.studio314.autowaremanagesys.handler;

import com.studio314.autowaremanagesys.interceptor.Limiting;
import com.studio314.autowaremanagesys.service.WareService;
import com.studio314.autowaremanagesys.utils.JWTUtils;
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
public class WareHandler {

    @Autowired
    WareService wareService;

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(HashMap.class)
            .flatMap(body -> {
                String wareName = (String) body.get("wareName");
                String token = request.headers().firstHeader("token");
                String userIdStr = JWTUtils.getUserId(token);
                int uID = Integer.parseInt(userIdStr);
                Result result = wareService.create(uID, wareName);
                return ok().contentType(MediaType.APPLICATION_JSON).bodyValue(result);
            });
    }



    public Mono<ServerResponse> query(ServerRequest request) {
        String token = request.headers().firstHeader("token");
        String userIdStr = JWTUtils.getUserId(token);
        int uID = Integer.parseInt(userIdStr);
        Result result = wareService.selectAll(uID);
        return ok().contentType(MediaType.APPLICATION_JSON).bodyValue(result);
    }

    @PreAuthorize("hasPermission(#request.pathVariable(\"id\"),'controller')")
    public Mono<ServerResponse> delete(ServerRequest request) {
        String token = request.headers().firstHeader("token");
        String userIdStr = JWTUtils.getUserId(token);
        int uID = Integer.parseInt(userIdStr);
        int id = Integer.parseInt(request.pathVariable("id"));
        Result result = wareService.delete(id, uID);
        return ok().contentType(MediaType.APPLICATION_JSON).bodyValue(result);
    }
}
