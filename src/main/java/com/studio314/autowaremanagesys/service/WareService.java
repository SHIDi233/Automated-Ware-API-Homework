package com.studio314.autowaremanagesys.service;

import com.studio314.autowaremanagesys.utils.Result;

public interface WareService {
    Result create(int uID, String wareName);

    Result selectAll(int uID);

    Result select(int id);

    Result delete(int id);

    //入库
    Result add(int wareID, int cargoID);

    //出库
    Result del(int wareID, int cargoID);
}
