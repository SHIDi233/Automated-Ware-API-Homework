package com.studio314.autowaremanagesys.handler;

import com.studio314.autowaremanagesys.service.EmployeeService;
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

@Component
public class EmployeeHandler {

    @Autowired
    EmployeeService employeeService;

    public Mono<ServerResponse> getEmployee(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("wID"));
        return ok().contentType(MediaType.APPLICATION_JSON).bodyValue(Result.success(employeeService.getEmployee(id)));
    }

    public Mono<ServerResponse> addEmployee(ServerRequest request) {
        return request.bodyToMono(HashMap.class)
                .flatMap(body -> {
                    String role = (String) body.get("role");
                    String mail = (String) body.get("email");
                    int wID = Integer.parseInt(request.pathVariable("wID"));
                    return ok().contentType(MediaType.APPLICATION_JSON).bodyValue(employeeService.addEmployee(wID, mail, role));
                });
    }

    public Mono<ServerResponse> deleteEmployee(ServerRequest request) {
        int uID = Integer.parseInt(request.pathVariable("uID"));
        int wID = Integer.parseInt(request.pathVariable("wID"));
        return ok().contentType(MediaType.APPLICATION_JSON).bodyValue(employeeService.deleteEmployee(wID, uID));
    }

}
