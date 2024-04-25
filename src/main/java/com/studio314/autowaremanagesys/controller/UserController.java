package com.studio314.autowaremanagesys.controller;

import com.studio314.autowaremanagesys.utils.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/query")
    @PreAuthorize("hasRole('user')")
    public String queryStudent() {
        return "Query success";
    }

    @GetMapping("/testLimit")
    public Result testLimit() {
        return Result.success("testLimit");
    }
}