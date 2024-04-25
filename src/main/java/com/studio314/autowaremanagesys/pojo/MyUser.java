package com.studio314.autowaremanagesys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUser {

    private String uName;
    private String password;
    private String uID;
    private String mail;

}
