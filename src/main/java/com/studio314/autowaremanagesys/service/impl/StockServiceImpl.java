package com.studio314.autowaremanagesys.service.impl;

import com.studio314.autowaremanagesys.mapper.CargoMapper;
import com.studio314.autowaremanagesys.mapper.StockMapper;
import com.studio314.autowaremanagesys.pojo.Cargo;
import com.studio314.autowaremanagesys.pojo.Stock;
import com.studio314.autowaremanagesys.service.StockService;
import com.studio314.autowaremanagesys.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    StockMapper stockMapper;

    @Autowired
    CargoMapper cargoMapper;

    @Override
    public Result queryAllStocks(int wID) {
        List<Stock> stocks = stockMapper.selectAllStocks(wID);
        List<Map<String, Object>> res = new ArrayList<>();
        for(Stock stock : stocks){
            Map<String, Object> map = new HashMap<>();
            map.put("cargoID", stock.getCargoID());
            map.put("num", stock.getNum());
            Cargo cargo = cargoMapper.select(stock.getCargoID());
            if(cargo != null){
                map.put("cargoName", cargo.getCargoName());
                map.put("cargoDescription", cargo.getCargoDescription());
            }
            res.add(map);
        }
        return Result.success(res);
    }

    @Override
    public Result createStock(int wID, int cargoID, int stockNum) {

        if (stockNum <= 0) {
            return Result.error("数量必须大于0");
        }

        Cargo cargo = cargoMapper.select(cargoID);
        if(cargo == null){
            return Result.error("货物不存在");
        }

        // 如果stock表里已经有了该货物，则直接增加数量，否则创建新的货物
        Stock stock = stockMapper.selectStock(wID, cargoID);
        if(stock != null){
            stockMapper.updateStock(wID, cargoID, stock.getNum() + stockNum);
        }else{
            stockMapper.insertStock(wID, cargoID, stockNum);
        }
        return Result.success();
    }

    @Override
    public Result outStock(int wID, int cargoID, int stockNum) {
        if (stockNum <= 0) {
            return Result.error("数量必须大于0");
        }

        Stock stock = stockMapper.selectStock(wID, cargoID);
        if(stock == null){
            return Result.error("货物不存在");
        }

        if(stock.getNum() < stockNum){
            return Result.error("库存不足");
        }

        stockMapper.updateStock(wID, cargoID, stock.getNum() - stockNum);
        return Result.success();
    }
}
