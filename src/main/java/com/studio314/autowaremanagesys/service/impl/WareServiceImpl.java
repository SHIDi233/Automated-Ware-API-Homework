package com.studio314.autowaremanagesys.service.impl;

import com.studio314.autowaremanagesys.mapper.WareMapper;
import com.studio314.autowaremanagesys.service.WareService;
import com.studio314.autowaremanagesys.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WareServiceImpl implements WareService {
    @Autowired
    WareMapper wm;

    @Override
    public Result create(int uID, String wareName) {
        wm.insert(uID, wareName);
        return Result.success();
    }

    @Override
    public Result selectAll(){
        return Result.success(wm.getAll());
    }

    //查库存
    @Override
    public Result select(int id){
        return Result.success(wm.get(id));
    }

    @Override
    public Result delete(int id){
        wm.delete(id);
        return Result.success();
    }

    //入库
    @Override
    public Result add(int wareID, int cargoID){
        //检验是否有

        return null;
    }

    //出库
    @Override
    public Result del(int wareID, int cargoID){
        return null;
    }
}
