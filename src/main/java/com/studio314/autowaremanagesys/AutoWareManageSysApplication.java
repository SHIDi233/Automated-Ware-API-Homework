package com.studio314.autowaremanagesys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AutoWareManageSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoWareManageSysApplication.class, args);
    }

}
