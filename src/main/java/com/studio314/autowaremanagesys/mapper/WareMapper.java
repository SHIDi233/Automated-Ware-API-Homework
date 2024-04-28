package com.studio314.autowaremanagesys.mapper;

import com.studio314.autowaremanagesys.pojo.Ware;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WareMapper {

    @Options(useGeneratedKeys = true, keyProperty = "wareID")
    @Insert("insert into Ware(creator, wareName) values(#{creator}, #{wareName})")
    void insert(Ware ware);

    @Select("select * from Ware where creator=#{uID} and isDel=FALSE")
    List<Ware> getAll(int uID);

    @Select("select * from Ware where wareID=#{id} and isDel=FALSE")
    List<Ware> get(int id);

    @Update("update Ware set isDel=True where wareID=#{id} and isDel=FALSE")
    void delete(int id);

    @Select("select * from Ware where wareID=#{wareID} and creator=#{creator};")
    Ware checkCreator(int wareID, int creator);
}
