package com.studio314.autowaremanagesys.controller;

import com.studio314.autowaremanagesys.interceptor.Limiting;
import com.studio314.autowaremanagesys.pojo.Cargo;
import com.studio314.autowaremanagesys.service.CargoService;
import com.studio314.autowaremanagesys.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cargo")
@Slf4j
public class CargoController {
    @Autowired
    CargoService cs;

    //查看全部货物种类
    @GetMapping("")
    @Limiting(limitNum = 10)
    @Cacheable(cacheNames = "allCargos", key = "'allCargos'")
    public Result queryAllCargos() {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Cargo> list = cs.getAllCargos();
        return Result.success(getCargoList(-1,list));
    }

    //查看某种货物种类
    @GetMapping("/{id}")
    @Limiting(limitNum = 10)
    public Result queryCargo(@PathVariable("id") int id) {
        Cargo cg = cs.getCargo(id);
        return Result.success(cg);
    }

    //增加货物根种类
    @PostMapping("")
    @Limiting(limitNum = 10)
    @PreAuthorize("hasRole('admin')")
    @CacheEvict(cacheNames = "allCargos", key = "'allCargos'")
    public Result addCargo(@RequestBody HashMap body) {
        String name = (String)body.get("name");
        String description = (String)body.get("description");
        log.info("{}-{}", name, description);
        int i = cs.addCargo(name, description);
        return Result.success(new HashMap<>() {{put("cargoId", i);}});
    }

    //增加货物子种类
    @PostMapping("/{cargoID}")
    @Limiting(limitNum = 10)
    @PreAuthorize("hasRole('admin')")
    @CacheEvict(cacheNames = "allCargos", key = "'allCargos'")
    public Result addCargo(@RequestBody HashMap body,@PathVariable int cargoID) {
        String name = (String)body.get("cargoName");
        String description = (String)body.get("cargoDescription");
        int parent = cargoID;
        cs.addCargo(name,description,parent);
        int i = cs.addCargo(name, description);
        return Result.success(new HashMap<>() {{put("cargoId", i);}});
    }

    //修改货物种类
    @PutMapping("/{cargoID}")
    @Limiting(limitNum = 10)
    @PreAuthorize("hasRole('admin')")
    @CacheEvict(cacheNames = "allCargos", key = "'allCargos'")
    public Result updateCargo(@RequestBody HashMap body,
                              @PathVariable int cargoID) {
        String name = (String)body.get("cargoName");
        String description = (String)body.get("cargoDescription");
        cs.updateCargo(cargoID ,name,description);
        return Result.success();
    }

    //删除货物种类
    @DeleteMapping("/{cargoID}")
    @Limiting(limitNum = 10)
    @PreAuthorize("hasRole('admin')")
    @CacheEvict(cacheNames = "allCargos", key = "'allCargos'")
    public Result deleteCargo(@PathVariable int cargoID) {
        cs.deleteCargo(cargoID);
        return Result.success();
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
