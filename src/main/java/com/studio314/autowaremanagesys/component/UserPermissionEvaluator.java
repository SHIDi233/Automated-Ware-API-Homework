package com.studio314.autowaremanagesys.component;

import com.studio314.autowaremanagesys.mapper.EmployeeMapper;
import com.studio314.autowaremanagesys.mapper.WareMapper;
//import com.studio314.autowaremanagesys.pojo.LoginUser;
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

    @Autowired
    WareMapper wm;

    @Autowired
    EmployeeMapper em;

    /**
     * hasPermission鉴权方法
     * @Param  permission 请求按钮的权限
     * @Return boolean 是否通过
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        Object principal = authentication.getPrincipal();
        int userID=((LoginUser) principal).getUser().getUID();
//        int userID=1;

        System.out.println("UserPermissionEvaluator1 hasPermission!!!");
        System.out.println(userID);
        System.out.println(targetUrl);
        System.out.println(permission);

        //搜索仓库表
        if(permission.equals("controller")){
            System.out.println(wm.checkCreator(Integer.parseInt((String) targetUrl),userID));
            if(wm.checkCreator(Integer.parseInt((String) targetUrl),userID)!=null)
                return true;
            else
                return false;
        }
        //搜索大表
        else if(permission.equals("user")){
            if(em.check(userID,Integer.parseInt((String) targetUrl))!=null)
                return true;
            else
                return false;
        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        System.out.println("UserPermissionEvaluator2 hasPermission!!!");
        return true;
    }
}
