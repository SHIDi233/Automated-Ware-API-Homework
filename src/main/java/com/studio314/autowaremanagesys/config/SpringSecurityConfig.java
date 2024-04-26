package com.studio314.autowaremanagesys.config;

import com.studio314.autowaremanagesys.component.ResponseFilter;
import com.studio314.autowaremanagesys.component.SpringSecurityHandler;
import com.studio314.autowaremanagesys.filter.JwtAuthenticationTokenFilter;
import com.studio314.autowaremanagesys.service.DBUserDetailService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((session) -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(request -> {
//                    request.anyRequest().permitAll();
                    request.requestMatchers(HttpMethod.POST , "/user/login").permitAll();
                    request.requestMatchers(HttpMethod.POST , "/user/register").permitAll();
                    request.requestMatchers(HttpMethod.POST , "/user/loginTest").permitAll();

//                    request.requestMatchers("/signup/signup").permitAll();
                    request.anyRequest().authenticated();
                })

                .addFilterBefore(responseFilter, WebAsyncManagerIntegrationFilter.class) // 在 Web...过滤器之前添加过滤器
                .formLogin(request -> { // 添加登录处理器
                    request.successHandler(handler)
                            .failureHandler(handler);
                })
                .exceptionHandling(request -> { // 添加访问拒绝处理
                    request.accessDeniedHandler(handler);
                })
                .logout(request -> { // 添加退出处理器
                    request.logoutSuccessHandler(handler)
                            .addLogoutHandler(handler);
                });
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
}

