package com.studio314.autowaremanagesys.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
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
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission,Object permission2) {
        authentication.getPrincipal();
//        Object principal = authentication.getPrincipal();
//        boolean hasPermission = false;
//        if(principal != null && principal instanceof UserDetails){
//            String name=((UserDetails) principal).getUsername();
//            SysUser sysUser=userRepository.findByName(name);
//            Set<String> permissions = new HashSet<>();
//            for(SysRole role : sysUser.getRoles()){
//                for(SysPermission sysPermission:role.getPermissions()){
//                    permissions.add(sysPermission.getName());
//                }
//            }
//
//            if (permissions.contains(permission.toString())){
//                return true;
//            }
//        }
        System.out.println("UserPermissionEvaluator1 hasPermission!!!");
        System.out.println(targetUrl);
        System.out.println(permission);
        System.out.println(permission2);
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        System.out.println("UserPermissionEvaluator2 hasPermission!!!");
        System.out.println("UserPermissionEvaluator2 hasPermission!!!");
        return true;
    }
}
