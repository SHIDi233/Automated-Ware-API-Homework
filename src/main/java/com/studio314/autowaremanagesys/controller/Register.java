package com.studio314.autowaremanagesys.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class Register {
    @PutMapping("/signup")
    public String demo1(String uName, String mail, String password) {
        System.out.println("demo");
        return "666";
    }
}
