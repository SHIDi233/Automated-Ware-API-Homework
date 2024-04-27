package com.studio314.autowaremanagesys.component;

import com.studio314.autowaremanagesys.pojo.LoginUser;
import com.studio314.autowaremanagesys.pojo.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class UserPermissionEvaluator implements PermissionEvaluator {


    /**
     * hasPermission鉴权方法
     * @Param  permission 请求按钮的权限
     * @Return boolean 是否通过
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        Object principal = authentication.getPrincipal();
        int id=((LoginUser) principal).getUser().getUID();

        System.out.println("UserPermissionEvaluator1 hasPermission!!!");
        System.out.println(id);
        System.out.println(targetUrl);
        System.out.println(permission);
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        System.out.println("UserPermissionEvaluator2 hasPermission!!!");
        System.out.println("UserPermissionEvaluator2 hasPermission!!!");
        return true;
    }
}
