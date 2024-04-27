package com.studio314.autowaremanagesys.pojo;

import com.studio314.autowaremanagesys.config.SecurityGrantedAuthority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUser implements Serializable {

    private String uName;
    private String password;
    private int uID;
    private String mail;
    private String role;

    transient List<SecurityGrantedAuthority> authorities;

}
