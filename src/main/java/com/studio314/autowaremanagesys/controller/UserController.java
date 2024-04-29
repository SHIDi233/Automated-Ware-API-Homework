package com.studio314.autowaremanagesys.controller;

import com.studio314.autowaremanagesys.interceptor.Limiting;
import com.studio314.autowaremanagesys.service.LoginService;
import com.studio314.autowaremanagesys.utils.JWTUtils;
import com.studio314.autowaremanagesys.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    LoginService loginService;

    @PostMapping("/register")
    @Limiting(limitNum = 1)
    public Result register(@RequestBody HashMap body){
        String name = (String) body.get("name");
        String mail = (String) body.get("mail");
        String password = (String) body.get("password");
        return loginService.register(name, mail, password);
    }

    @PostMapping("/login")
    @Limiting(limitNum = 1)
    public Result login(@RequestBody HashMap body) {
        String mail = (String) body.get("mail");
        String password = (String) body.get("password");
        log.info("用户尝试登录：" + mail);
        return loginService.login(mail, password);
    }
}