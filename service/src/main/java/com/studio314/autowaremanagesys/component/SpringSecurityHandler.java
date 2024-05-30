//package com.studio314.autowaremanagesys.component;
//
//import com.alibaba.fastjson2.JSON;
//import com.studio314.autowaremanagesys.utils.Result;
//import io.netty.handler.codec.http.HttpRequest;
//import io.netty.handler.codec.http.HttpResponse;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@Component
//public class SpringSecurityHandler
//        implements AuthenticationSuccessHandler, AuthenticationFailureHandler
//        , AccessDeniedHandler, LogoutSuccessHandler, LogoutHandler {
//    @Override
//    public void onAuthenticationFailure(HttpRequest request, HttpResponse response, AuthenticationException exception) throws IOException, ServletException, IOException {
////        PrintWriter writer = response.getWriter();
////        writer.println("登录失败");
//    }
//
//    @Override
//    public void onAuthenticationSuccess(HttpRequest request, HttpResponse response, Authentication authentication) throws IOException, ServletException {
////        PrintWriter writer = response.getWriter();
////        writer.println("登录成功");
//    }
//
//    @Override
//    public void handle(HttpRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
////        PrintWriter writer = response.getWriter();
////        writer.println(JSON.toJSON(Result.error("无权限")));
//    }
//
//    @Override
//    public void logout(HttpRequest request, HttpServletResponse response, Authentication authentication) {
//        PrintWriter writer = null;
//        try {
////            writer = response.getWriter();
//            writer.println("正在退出账号");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void onLogoutSuccess(HttpRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
////        PrintWriter writer = response.getWriter();
////        writer.println("退出账号成功");
//    }
//}
