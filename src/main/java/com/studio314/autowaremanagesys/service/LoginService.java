package com.studio314.autowaremanagesys.service;

import com.studio314.autowaremanagesys.pojo.MyUser;
import com.studio314.autowaremanagesys.utils.Result;

public interface LoginService {
    Result login(String mail, String password);

    Result register(String name, String mail, String password);
}
