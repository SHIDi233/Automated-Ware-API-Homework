package com.studio314.autowaremanagesys.controller;

import com.studio314.autowaremanagesys.service.WareService;
import com.studio314.autowaremanagesys.utils.JWTUtils;
import com.studio314.autowaremanagesys.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wares")
public class WareController {

    @Autowired
    WareService ws;

    @PostMapping("")
    public Result create(@RequestParam("wareName") String wareName, HttpServletRequest request){
        String token = request.getHeader("token");
        String userIdStr = JWTUtils.getUserId(token);
        if (userIdStr == null){
            return Result.error("token错误");
        }
        int userId = Integer.parseInt(userIdStr);
        return ws.create(userId, wareName);
    }

    @GetMapping("")
    public Result query(){
        return ws.selectAll();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id){
        return ws.delete(id);
    }
}
