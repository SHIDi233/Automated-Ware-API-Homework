package com.studio314.autowaremanagesys.mapper;

import com.studio314.autowaremanagesys.pojo.MyUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from User where mail = #{mail}")
    MyUser getUserByMail(String mail);

    @Select("select cnt from User where uName='apple'")
    int test1();

    @Update("update User set cnt=cnt+1 where uName='apple'")
    void test2();

    @Insert("insert into User(uName, mail, password) values(#{name}, #{mail}, #{password})")
    void registerUser(String name, String mail, String password);
}
