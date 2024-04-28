package com.studio314.autowaremanagesys.controller;

import com.studio314.autowaremanagesys.service.StockService;
import com.studio314.autowaremanagesys.utils.JWTUtils;
import com.studio314.autowaremanagesys.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wares/{wID}/stock")
public class StockController {

    @Autowired
    StockService stockService;

    /**
     * 查询所有货物
     * @return 货物列表
     */
    @GetMapping
    public Result query(@PathVariable("wID") int wID){
        return stockService.queryAllStocks(wID);
    }

    /**
     * 入库，若仓库已经有该货物，则增加数量，否则创建新的货物
     * @param wID 仓库ID
     * @param cargoID 货物ID
     * @param stockNum 货物数量
     * @param request 请求
     * @return 结果
     */
    @PostMapping
    public Result create(@PathVariable("wID") int wID, @RequestParam("cargoID") int cargoID, @RequestParam("stockNum") int stockNum, HttpServletRequest request){
        String token = request.getHeader("token");
        String userIdStr = JWTUtils.getUserId(token);
        if (userIdStr == null) {
            return Result.error("您没有访问此的权限");
        }
        return stockService.createStock(wID, cargoID, stockNum);
    }

    /**
     * 出库，减少货物数量
     * @param wID
     * @param cargoID
     * @param stockNum
     * @param request
     * @return
     */
    @PutMapping
    public Result out(@PathVariable("wID") int wID, @RequestParam("cargoID") int cargoID, @RequestParam("stockNum") int stockNum, HttpServletRequest request){
        String token = request.getHeader("token");
        String userIdStr = JWTUtils.getUserId(token);
        if (userIdStr == null) {
            return Result.error("您没有访问此的权限");
        }
        return stockService.outStock(wID, cargoID, stockNum);
    }

}
