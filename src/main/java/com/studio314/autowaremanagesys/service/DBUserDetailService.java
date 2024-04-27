package com.studio314.autowaremanagesys.service;

import com.studio314.autowaremanagesys.config.SecurityGrantedAuthority;
import com.studio314.autowaremanagesys.mapper.UserMapper;
import com.studio314.autowaremanagesys.pojo.LoginUser;
import com.studio314.autowaremanagesys.pojo.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DBUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        MyUser myUser = userMapper.getUserByMail(mail);
        System.out.println(myUser);
        if(myUser == null){
            throw new UsernameNotFoundException(mail);
        }

        return new LoginUser(myUser);
    }
}

