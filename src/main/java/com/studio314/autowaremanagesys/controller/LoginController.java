package com.studio314.autowaremanagesys.controller;

import com.studio314.autowaremanagesys.interceptor.Limiting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/1")
    @Limiting(limitNum = 1, name = "limiting1")
    public String demo() {
        return "spring security demo";
    }
}
