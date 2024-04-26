package com.studio314.autowaremanagesys.controller;

import com.studio314.autowaremanagesys.service.LoginService;
import com.studio314.autowaremanagesys.utils.JWTUtils;
import com.studio314.autowaremanagesys.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    LoginService loginService;

    @GetMapping("/query")
    @PreAuthorize("hasRole('user')")
    public String queryStudent() {
        return "Query success";
    }

    @GetMapping("/testLimit")
    public Result testLimit() {
        return Result.success("testLimit");
    }

    @PostMapping("/register")
    public Result register(@RequestParam("name") String name,
                           @RequestParam("mail") String mail,
                           @RequestParam("password") String password){
        return loginService.register(name, mail, password);
    }

    @GetMapping("/login")
    public Result login(@RequestParam("mail") String mail, @RequestParam("password") String password) {
        log.info("用户尝试登录：" + mail);
        return loginService.login(mail, password);
    }

    @PostMapping("/loginTest")
    public Result loginTest(HttpServletRequest request){
        String token = request.getHeader("token");
        JWTUtils.getUserId(token);
        log.info("用户尝试登录：");
        return Result.success();
    }
}