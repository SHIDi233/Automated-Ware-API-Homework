package com.studio314.autowaremanagesys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/1")
    public String demo() {
        return "spring security demo";
    }
}
