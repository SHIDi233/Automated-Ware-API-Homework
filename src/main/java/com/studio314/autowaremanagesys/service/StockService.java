package com.studio314.autowaremanagesys.service;

import com.studio314.autowaremanagesys.utils.Result;

public interface StockService {


    Result queryAllStocks(int wID);

    Result createStock(int wID, int stockID, int stockNum);

    Result outStock(int wID, int cargoID, int stockNum);
}
