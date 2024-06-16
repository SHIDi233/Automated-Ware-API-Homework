package com.studio314.autowaremanagesys.filter;

import com.studio314.autowaremanagesys.config.RedisCache;
import com.studio314.autowaremanagesys.pojo.LoginUser;
import com.studio314.autowaremanagesys.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import java.util.Objects;

@Component
public class NettyJwtAuthenticationTokenFilter implements WebFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // 在这里添加你的过滤逻辑
        // 比如检查JWT token或其他认证信息
        // 如果认证成功，则调用chain.filter(exchange)继续请求处理
        // 如果失败，则返回一个响应表示未授权等

        boolean shouldFilter = exchange.getRequest().getURI().getPath().contains("/v2/users/login") ||
                exchange.getRequest().getURI().getPath().contains("/v2/users/register");
        if (shouldFilter) {
            return chain.filter(exchange);
        }
        else{
            String token = exchange.getRequest().getHeaders().getFirst("token");

            System.out.println("NettyJwtAuthenticationTokenFilter");
            System.out.println("token:"+token);

            if(token==null){
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.FORBIDDEN);
                return response.setComplete();
            }
            else{
                String userId = JWTUtils.getUserId(token);
                System.out.println("userId:"+userId);
                String redisKey = "login:" + userId;
                LoginUser loginUser = redisCache.getCacheObject(redisKey);
                System.out.println("loginUser:"+loginUser);
                if (Objects.isNull(loginUser)){
                    System.out.println("用户未登录！");
                    ServerHttpResponse response = exchange.getResponse();
                    response.setStatusCode(HttpStatus.FORBIDDEN);
                    return response.setComplete();
                }

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                return chain.filter(exchange);
            }
        }


    }
}
