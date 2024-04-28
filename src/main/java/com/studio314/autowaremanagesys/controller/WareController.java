package com.studio314.autowaremanagesys.controller;

import com.studio314.autowaremanagesys.service.WareService;
import com.studio314.autowaremanagesys.utils.JWTUtils;
import com.studio314.autowaremanagesys.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/wares")
public class WareController {

    @Autowired
    WareService ws;

    //创建仓库
    @PostMapping("")
    public Result create(@RequestBody HashMap body,HttpServletRequest request){
        String wareName = (String)body.get("wareName");
        String token = request.getHeader("token");
        String userIdStr = JWTUtils.getUserId(token);
        if (userIdStr == null){
            return Result.error("token错误");
        }
        int userId = Integer.parseInt(userIdStr);
        return ws.create(userId, wareName);
    }

    //获取仓库列表
    @GetMapping("")
    public Result query(HttpServletRequest request){
        String token = request.getHeader("token");
        String userIdStr = JWTUtils.getUserId(token);
        if (userIdStr == null){
            return Result.error("token错误");
        }
        int uID = Integer.parseInt(userIdStr);
        return ws.selectAll(uID);
    }

    //删除仓库
    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission(#id,'controller')")
    public Result delete(@PathVariable int id,HttpServletRequest request){
        String token = request.getHeader("token");
        String userIdStr = JWTUtils.getUserId(token);
        if (userIdStr == null){
            return Result.error("token错误");
        }
        int uID = Integer.parseInt(userIdStr);
        return ws.delete(id,uID);
    }
}
