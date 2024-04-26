package com.studio314.autowaremanagesys.mapper;

import com.studio314.autowaremanagesys.pojo.Cargo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CargoMapper {
    @Select("select * from Cargo where isDel=FALSE")
    List<Cargo> selectAll();

    @Select("select * from Cargo where cargoID=#{id} and isDel=FALSE")
    Cargo select(int id);

    @Insert("insert into Cargo(cargoName,description,parent) values(#{cargoName},#{description},#{parent})")
    void insert(String cargoName, String cargoDescription, int parent);

    @Update("update Cargo set cargoName=#{cargoName},description=#{description} where cargoID=#{cargoID}) and isDel=FALSE")
    void update(int cargoID, String cargoName, String cargoDescription);

    @Update("update Cargo set isDel=TRUE where cargoID=#{cargoID}) and isDel=FALSE")
    void delete(int cargoID);
}
