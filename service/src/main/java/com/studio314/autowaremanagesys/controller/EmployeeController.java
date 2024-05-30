//package com.studio314.autowaremanagesys.controller;
//
//import com.studio314.autowaremanagesys.interceptor.Limiting;
//import com.studio314.autowaremanagesys.service.EmployeeService;
//import com.studio314.autowaremanagesys.utils.Result;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//
//@RestController
//@RequestMapping("/wares/{wID}/auth")
//@Slf4j
//public class EmployeeController {
//
//    @Autowired
//    EmployeeService es;
//
//    //获得所有员工
//    @GetMapping
//    @Limiting(limitNum = 10)
//    @PreAuthorize("hasPermission(#wID,'controller')")
//    @Cacheable(cacheNames = "employee", key = "'employee:'+#wID")
//    public Result getEmployee(@PathVariable int wID) {
//        log.info("请求所有员工");
//        return es.getEmployee(wID);
//    }
//
//    //添加员工
//    @PostMapping
//    @Limiting(limitNum = 10)
//    @PreAuthorize("hasPermission(#wID,'controller')")
//    @CacheEvict(cacheNames = "employee", key = "'employee:'+#wID")
//    public Result addEmployee(@PathVariable int wID, @RequestBody HashMap body){
//        String email = (String) body.get("email");
//        String role = (String) body.get("role");
//
//        log.info("添加员工 email:{}, role:{}", email, role);
//        return es.addEmployee(wID,email,role);
//    }
//
//    //删除员工
//    @DeleteMapping("/{uID}")
//    @Limiting(limitNum = 10)
//    @PreAuthorize("hasPermission(#wID,'controller')")
//    @CacheEvict(cacheNames = "employee", key = "'employee:'+#wID")
//    public Result deleteEmployee(@PathVariable int uID,@PathVariable int wID){
//        log.info("删除员工 uID:{} 位于仓库wID:{}", uID, wID);
//        return es.deleteEmployee(wID,uID);
//    }
//}
