package com.studio314.autowaremanagesys.filter;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.studio314.autowaremanagesys.config.RedisCache;
import com.studio314.autowaremanagesys.pojo.LoginUser;
import com.studio314.autowaremanagesys.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.studio314.autowaremanagesys.config.SecurityGrantedAuthority;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(ServerRequest request, ServerResponse response, Chain filterChain) throws ServletException, IOException, ServletException {
        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request,response);
            return;
        }
        //解析token
        String userId = JWTUtils.getUserId(token);
        System.out.println("userId:"+userId);
        //从redis中获取用户信息
//        userId = userId.substring(userId.indexOf("=") + 1 , userId.indexOf("}"));
        String redisKey = "login:" + userId;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        System.out.println(loginUser);
        if (Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录！");
        }
        //存入SecurityContextHolder
        // TODO 获取权限信息封装到Authentication中
        //获取权限信息
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //放行
        filterChain.doFilter(request,response);
    }
}
