package com.studio314.autowaremanagesys.mapper;

import com.studio314.autowaremanagesys.pojo.MyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from User where mail = #{mail}")
    MyUser getUserByMail(String mail);

}
