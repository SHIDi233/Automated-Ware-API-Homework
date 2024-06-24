package com.studio314.autowaremanagesys.mapper;

import com.studio314.autowaremanagesys.pojo.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employeeList where uID=#{uID} and wID=#{wID} and isDel=FALSE")
    Employee check(int uID, int wID);

    @Insert("insert into employeeList(uID,role,wID) values(#{uID},#{role},#{wID})")
    void add(int uID, String role, int wID);

    @Update("update employeeList set isDel=TRUE where uID=#{uID} and wID=#{wID}")
    void del(int uID, int wID);

    @Select("select * from employeeList where wID=#{wID} and isDel=FALSE")
    List<Employee> get(int wID);
}
