package com.studio314.autowaremanagesys.service;

import com.studio314.autowaremanagesys.utils.Result;

public interface WareService {
    Result create(int uID, String wareName);

    Result selectAll(int uID);

    Result delete(int id,int uID);

}
