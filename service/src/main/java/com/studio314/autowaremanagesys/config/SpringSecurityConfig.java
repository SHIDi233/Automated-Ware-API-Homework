package com.studio314.autowaremanagesys.config;

//import com.studio314.autowaremanagesys.component.ResponseFilter;
//import com.studio314.autowaremanagesys.component.SpringSecurityHandler;
import com.studio314.autowaremanagesys.component.UserPermissionEvaluator;
//import com.studio314.autowaremanagesys.filter.JwtAuthenticationTokenFilter;
import com.studio314.autowaremanagesys.filter.NettyJwtAuthenticationTokenFilter;
import com.studio314.autowaremanagesys.handler.AuthError;
import com.studio314.autowaremanagesys.service.DBUserDetailService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;

//@EnableWebSecurity
@EnableWebFluxSecurity
@Configuration
@EnableMethodSecurity
@EnableGlobalAuthentication
public class SpringSecurityConfig {
    // SpringSecurityHandler 处理器
//    @Resource
//    private SpringSecurityHandler handler;
//    // 修改输出流的输出格式
//    @Resource
//    private ResponseFilter responseFilter;
//
//    @Autowired
//    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Resource
    private AuthError handle;

    @Autowired
    private NettyJwtAuthenticationTokenFilter nettyJwtAuthenticationTokenFilter;

    @Autowired
    private UserPermissionEvaluator upe;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http){
//            throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement((session) -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
////                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests(request -> {
////                    request.anyRequest().permitAll();
//                    request.requestMatchers(HttpMethod.POST, "/user/login").permitAll();
//                    request.requestMatchers(HttpMethod.POST, "/user/register").permitAll();
//                    request.requestMatchers(HttpMethod.POST, "/user/loginTest").permitAll();
//
////                    request.requestMatchers("/signup/signup").permitAll();
//                    request.anyRequest().authenticated();
//                });
////                .addFilterBefore(responseFilter, WebAsyncManagerIntegrationFilter.class) // 在 Web...过滤器之前添加过滤器
////                .formLogin(request -> { // 添加登录处理器
////                    request.successHandler(handler)
////                            .failureHandler(handler);
////                })
////                .exceptionHandling(request -> { // 添加访问拒绝处理
////                    request.accessDeniedHandler(handler);
////                })
////                .logout(request -> { // 添加退出处理器
////                    request.logoutSuccessHandler(handler)
////                            .addLogoutHandler(handler);
////                });
//        return http.build();
//        http.authorizeExchange(exchanges -> exchanges // 对于请求进行匹配
//                .pathMatchers("/test1").permitAll()
//                .pathMatchers("/test2").hasAnyRole("root")
//                .pathMatchers("/test3").hasAnyRole("admin")
//                .pathMatchers("/test4").hasAnyAuthority("root","user")
//                .anyExchange().authenticated()
//        );
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges // 对于请求进行匹配
//                    .pathMatchers("/v2/users/login").permitAll()
                    .anyExchange().permitAll()
//                    .anyExchange().authenticated()
                )
                .exceptionHandling(request -> { // 添加访问拒绝处理
                    request.accessDeniedHandler(handle);
                })
                .addFilterAt(nettyJwtAuthenticationTokenFilter, SecurityWebFiltersOrder.AUTHENTICATION);


        return http.build();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    static MethodSecurityExpressionHandler methodSecurityExpressionHandler(UserPermissionEvaluator permissionEvaluator) {
        DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
        handler.setPermissionEvaluator(permissionEvaluator);
        return handler;
    }
}

