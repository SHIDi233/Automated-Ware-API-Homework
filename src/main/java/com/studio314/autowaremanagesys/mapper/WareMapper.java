package com.studio314.autowaremanagesys.mapper;

import com.studio314.autowaremanagesys.pojo.Ware;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface WareMapper {
    @Insert("insert into Ware(wareName) values(#{wareName})")
    void insert(String wareName);

    @Select("select * from Ware where isDel=FALSE")
    List<Ware> getAll();

    @Select("select * from Ware where wareID=#{id} and isDel=FALSE")
    List<Ware> get(int id);

    @Update("update Ware set isDel=True where wareID=#{id} and isDel=FALSE")
    void delete(int id);
}
