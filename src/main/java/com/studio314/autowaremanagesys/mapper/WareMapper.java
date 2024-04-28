package com.studio314.autowaremanagesys.mapper;

import com.studio314.autowaremanagesys.pojo.Ware;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface WareMapper {
    @Insert("insert into Ware(creator, wareName) values(#{uID}, #{wareName})")
    void insert(int uID, String wareName);

    @Select("select * from Ware where creator=#{uID} and isDel=FALSE")
    List<Ware> getAll(int uID);

    @Select("select * from Ware where wareID=#{id} and isDel=FALSE")
    List<Ware> get(int id);

    @Update("update Ware set isDel=True where wareID=#{id} and isDel=FALSE")
    void delete(int id);

    @Select("select * from Ware where wareID=#{wareID} and creator=#{creator};")
    Ware checkCreator(int wareID, int creator);
}
