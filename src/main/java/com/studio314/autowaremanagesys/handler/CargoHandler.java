package com.studio314.autowaremanagesys.handler;

import com.studio314.autowaremanagesys.pojo.Cargo;
import com.studio314.autowaremanagesys.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class CargoHandler {

    @Autowired
    CargoService cargoService;

    public Mono<ServerResponse> queryAllCargos(ServerRequest request) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Result.success(getCargoList(-1, cargoService.getAllCargos())));
    }

    public Mono<ServerResponse> queryCargo(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("cargoID"));
        Cargo cg = cargoService.getCargo(id);
        return ok().contentType(MediaType.APPLICATION_JSON).bodyValue(Result.success(cg));
    }

    @PreAuthorize("hasRole('admin')")
    public Mono<ServerResponse> addCargoRoot(ServerRequest request) {
        return request.bodyToMono(HashMap.class)
                .flatMap(body -> {
                    String name = (String) body.get("cargoName");
                    String description = (String) body.get("cargoDescription");
                    int i = cargoService.addCargo(name, description);
                    return ok().contentType(MediaType.APPLICATION_JSON).bodyValue(Result.success(new HashMap<>() {{put("cargoId", i);}}));
                });
    }

    @PreAuthorize("hasRole('admin')")
    public Mono<ServerResponse> addCargo(ServerRequest request) {
        return request.bodyToMono(HashMap.class)
                .flatMap(body -> {
                    String name = (String) body.get("cargoName");
                    String description = (String) body.get("cargoDescription");
                    int parent = Integer.parseInt(request.pathVariable("cargoID"));
                    int i = cargoService.addCargo(name, description, parent);
                    return ok().contentType(MediaType.APPLICATION_JSON).bodyValue(Result.success(new HashMap<>() {{put("cargoId", i);}}));
                });
    }

    @PreAuthorize("hasRole('admin')")
    public Mono<ServerResponse> deleteCargo(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("cargoID"));
        cargoService.deleteCargo(id);
        return ok().contentType(MediaType.APPLICATION_JSON).bodyValue(Result.success());
    }

    @PreAuthorize("hasRole('admin')")
    public Mono<ServerResponse> updateCargo(ServerRequest request) {
        return request.bodyToMono(HashMap.class)
                .flatMap(body -> {
                    int id = Integer.parseInt(request.pathVariable("cargoID"));
                    String name = (String) body.get("cargoName");
                    String description = (String) body.get("cargoDescription");
                    cargoService.updateCargo(id, name, description);
                    return ok().contentType(MediaType.APPLICATION_JSON).bodyValue(Result.success());
                });
    }

    private List<Map<String, Object>> getCargoList(int cargoID, List<Cargo> list){
        System.out.println(list);
        List<Map<String, Object>> result = new ArrayList<>();
        int flag=0;
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getParent()==cargoID){
                Map<String, Object> res = new HashMap<>();
                res.put("id", list.get(i).getCargoID());
                res.put("name", list.get(i).getCargoName());
                res.put("description", list.get(i).getCargoDescription());
                res.put("childrenTypes", getCargoList(list.get(i).getCargoID(), list));
                flag++;
                result.add(res);
            }
        }
        if(flag==0)
            return null;
        return result;
    }

}
