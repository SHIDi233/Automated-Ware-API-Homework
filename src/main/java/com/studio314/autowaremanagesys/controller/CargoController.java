package com.studio314.autowaremanagesys.controller;

import com.studio314.autowaremanagesys.pojo.Cargo;
import com.studio314.autowaremanagesys.service.CargoService;
import com.studio314.autowaremanagesys.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cargo")
public class CargoController {
    @Autowired
    CargoService cs;

    //查看全部货物种类
    @GetMapping("")
    public Result queryAllCargos() {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Cargo> list = cs.getAllCargos();
        return Result.success(getCargoList(-1,list));
    }

    //查看某种货物种类
    @GetMapping("/{id}")
    public Result queryCargo(@PathVariable("id") int id) {
        Cargo cg = cs.getCargo(id);
        return Result.success(cg);
    }

    //增加货物根种类
    @PostMapping("")
    public Result addCargo(@RequestParam("cargoName") String name,
                           @RequestParam("cargoDescription") String description) {
        cs.addCargo(name,description);
        return Result.success();
    }

    //增加货物子种类
    @PostMapping("/{cargoID}")
    public Result addCargo(@RequestParam("cargoName") String name,
                           @RequestParam("cargoDescription") String description,
                           @PathVariable int cargoID) {
        int parent = cargoID;
        cs.addCargo(name,description,parent);
        return Result.success();
    }

    //修改货物种类
    @PutMapping("/{cargoID}")
    public Result updateCargo(@RequestParam("cargoName") String name,
                              @RequestParam("cargoDescription") String description,
                              @PathVariable int cargoID) {
        cs.updateCargo(cargoID ,name,description);
        return Result.success();
    }

    //删除货物种类
    @DeleteMapping("/{cargoID}")
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
                res.put("id", list.get(i).getCargoId());
                res.put("name", list.get(i).getCargoName());
                res.put("description", list.get(i).getCargoDescription());
                res.put("childrenTypes", getCargoList(list.get(i).getCargoId(), list));
                flag++;
                result.add(res);
            }
        }
        if(flag==0)
            return null;
        return result;
    }
}
