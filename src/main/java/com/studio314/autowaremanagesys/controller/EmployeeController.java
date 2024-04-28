package com.studio314.autowaremanagesys.controller;

import com.studio314.autowaremanagesys.service.EmployeeService;
import com.studio314.autowaremanagesys.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wares/{wID}/auth")
public class EmployeeController {

    @Autowired
    EmployeeService es;

    //获得所有员工
    @GetMapping
    @PreAuthorize("hasPermission(#wID,'controller')")
    @Cacheable(cacheNames = "employee", key = "'employee:'+#wID")
    public Result getEmployee(@PathVariable int wID) {
        return es.getEmployee(wID);
    }

    //添加员工
    @PostMapping
    @PreAuthorize("hasPermission(#wID,'controller')")
    @CacheEvict(cacheNames = "employee", key = "'employee:'+#wID")
    public Result addEmployee(@PathVariable int wID,@RequestParam("email") String email,@RequestParam("role") String role){
        return es.addEmployee(wID,email,role);
    }

    //删除员工
    @DeleteMapping
    @PreAuthorize("hasPermission(#wID,'controller')")
    @CacheEvict(cacheNames = "employee", key = "'employee:'+#wID")
    public Result deleteEmployee(@PathVariable int wID,@RequestParam("uID") int uID){
        return es.deleteEmployee(wID,uID);
    }
}
