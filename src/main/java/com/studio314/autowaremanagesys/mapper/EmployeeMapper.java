package com.studio314.autowaremanagesys.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employeeList where uID=#{uID} and wID=#{wID}")
    Object check(int uID, int wID);

}
