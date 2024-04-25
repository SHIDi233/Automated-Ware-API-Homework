//package com.studio314.autowaremanagesys.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//public class MySecurity {
//
//    private AuthenticationManager authenticationManager;
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        // @formatter:off
////        System.out.println("Security Filter Chain Start!!!");
////        http
////                .authorizeHttpRequests((authorize) -> authorize
////                        .anyRequest().authenticated()
////                )
////                .httpBasic(withDefaults())
////                .formLogin(withDefaults());
////        // @formatter:on
////        return http.build();
////    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        System.out.println(">>>>>>>>>>  进入securityFilterChain");
////        http
//////        http.formLogin((formLogin) -> formLogin
//////            .loginPage("/login1.html") // 指定自定义登录页面地址
////////            .loginProcessingUrl("/user/login") // 登录访问路径：前台界面提交表单之后跳转到这个路径进行UserDetailsService的验证，必须和表单提交接口一样
//////            .defaultSuccessUrl("/admin/demo") // 认证成功之后跳转的路径
//////            .permitAll()
//////        );
////        .formLogin(withDefaults());
////        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
////            // 设置哪些路径可以直接访问，不需要认证
////            .anyRequest().authenticated() // 其他路径的请求都需要认证
////        );
////        http.csrf((csrf) -> csrf.disable());
//        http
//		.authorizeRequests(authorize -> authorize
//			.anyRequest().authenticated()
//		)
//		.formLogin(withDefaults())
//		.httpBasic(withDefaults());
//	    return http.build();
//    }
//
////     @Bean
////    public AuthenticationManager authenticationManager(
////            UserDetailsService userDetailsService,
////            PasswordEncoder passwordEncoder
////    ) {
////        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
////        // 认证逻辑的提供者，设置UserDetailsService
////        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
////        // 认证逻辑的提供者，设置PasswordEncoder
////        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
////
////        authenticationManager = new ProviderManager(daoAuthenticationProvider);
////        return authenticationManager;
////    }
////
////    @Bean
////    public UserDetailsService userDetailsService() {
////        UserDetails user = User.withDefaultPasswordEncoder()
////                .username("jay")
////                .password("123456")
////                .roles("user")
////                .build();
////
////        UserDetails admin = User.withDefaultPasswordEncoder()
////                .username("admin")
////                .password("123456")
////                // 注意：roles和authorities不能同时配置，同时配置后者会覆盖前者的权限
////                .authorities("ROLE_admin", "ROLE_user", "user:api", "order:api")
////                .build();
////
////        return new InMemoryUserDetailsManager(user, admin);
////    }
//
////
////    // @formatter:off
////    @Bean
////    public InMemoryUserDetailsManager userDetailsService() {
////        UserDetails user = User.withDefaultPasswordEncoder()
////                .username("user")
////                .password("12345")
////                .roles("USER")
////                .build();
////        return new InMemoryUserDetailsManager(user);
////    }
////
//    @Bean
//    public UserDetailsService users() {
//        UserDetails user = User.builder()
//            .username("user")
//            .password("{bcrypt}111")
//            .roles("USER")
//            .build();
//        UserDetails admin = User.builder()
//            .username("admin")
//            .password("{bcrypt}111")
//            .roles("USER", "ADMIN")
//            .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();  // 不加密
//        //return new BCryptPasswordEncoder(); // 加密方式bcrypt
//    }
//}
