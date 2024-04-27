package com.studio314.autowaremanagesys.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wares")
public class WareController {
    @PostMapping("/")
    public void create(@RequestParam("wareName") String wareName){

    }
}
