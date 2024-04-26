package com.studio314.autowaremanagesys.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {
    @PostMapping("/signup")
    public String signup(String uName, String mail, String password) {
        System.out.println("demo"+uName+":"+mail+":"+password);
        return "666";
    }
}
