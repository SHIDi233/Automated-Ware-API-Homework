package com.studio314.autowaremanagesys.service.impl;

import com.studio314.autowaremanagesys.mapper.WareMapper;
import com.studio314.autowaremanagesys.service.WareService;
import com.studio314.autowaremanagesys.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WareServiceImpl implements WareService {
    @Autowired
    WareMapper wm;

    @Override
    @CacheEvict(cacheNames = "wares", key = "'wares:'+#uID")
    public Result create(int uID, String wareName) {
        wm.insert(uID, wareName);
        return Result.success();
    }

    @Override
    @Cacheable(cacheNames = "wares", key = "'wares:'+#uID")
    public Result selectAll(int uID){
        return Result.success(wm.getAll(uID));
    }

    @Override
    @CacheEvict(cacheNames = "wares", key = "'wares:'+#uID")
    public Result delete(int id){
        wm.delete(id);
        return Result.success();
    }
}
