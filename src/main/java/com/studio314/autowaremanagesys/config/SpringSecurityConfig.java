package com.studio314.autowaremanagesys.config;

import com.studio314.autowaremanagesys.component.ResponseFilter;
import com.studio314.autowaremanagesys.component.SpringSecurityHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
@EnableGlobalAuthentication
public class SpringSecurityConfig {
    // SpringSecurityHandler 处理器
    @Resource
    private SpringSecurityHandler handler;
    // 修改输出流的输出格式
    @Resource
    private ResponseFilter responseFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http    .authorizeHttpRequests(request -> {
                    request.anyRequest().permitAll();
//                    request.requestMatchers( "/1" , "/signup").permitAll();
//                    request.requestMatchers("/signup/signup").permitAll();
//                    request.anyRequest().authenticated();
                })

                .addFilterBefore(responseFilter, WebAsyncManagerIntegrationFilter.class) // 在 Web...过滤器之前添加过滤器
                .formLogin(request -> { // 添加登录处理器
                    request.successHandler(handler)
                            .failureHandler(handler);
                })
//                .exceptionHandling(request -> { // 添加访问拒绝处理
//                    request.accessDeniedHandler(handler);
//                })
                .logout(request -> { // 添加退出处理器
                    request.logoutSuccessHandler(handler)
                            .addLogoutHandler(handler);
                });
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

