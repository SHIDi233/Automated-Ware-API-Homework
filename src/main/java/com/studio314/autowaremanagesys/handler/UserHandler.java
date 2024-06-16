package com.studio314.autowaremanagesys.handler;

import com.studio314.autowaremanagesys.interceptor.Limiting;
import com.studio314.autowaremanagesys.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import com.studio314.autowaremanagesys.utils.Result;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class UserHandler {

    @Autowired
    LoginService loginService;

    @Limiting(limitNum = 10)
    public Mono<ServerResponse> register(ServerRequest request) {
        return request.bodyToMono(HashMap.class)
                .flatMap(body -> {
                    String name = (String) body.get("name");
                    String mail = (String) body.get("mail");
                    String password = (String) body.get("password");
                    Result result = loginService.register(name, mail, password);
                    return ok().contentType(MediaType.APPLICATION_JSON).bodyValue(result);
                });
    }

    @Limiting(limitNum = 10)
    public Mono<ServerResponse> login(ServerRequest request) {
        return request.bodyToMono(HashMap.class)
                .flatMap(body -> {
                    String mail = (String) body.get("mail");
                    String password = (String) body.get("password");
                    System.out.println(mail+":"+password);
                    Result result = loginService.login(mail, password);
                    return ok().contentType(MediaType.APPLICATION_JSON).bodyValue(result);
                });
    }

}
