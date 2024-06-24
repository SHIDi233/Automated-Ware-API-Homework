package com.studio314.autowaremanagesys.mapper;

import com.studio314.autowaremanagesys.pojo.Cargo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CargoMapper {
    @Select("select * from Cargo where isDel=FALSE")
    List<Cargo> selectAll();

    @Select("select * from Cargo where cargoID=#{id} and isDel=FALSE")
    Cargo select(int id);

    @Options(useGeneratedKeys = true, keyProperty = "cargoID")
    @Insert("insert into Cargo(cargoName,cargoDescription,parent) values(#{cargoName},#{cargoDescription},#{parent})")
    void insert(Cargo cargo);

    @Update("update Cargo set cargoName=#{cargoName},cargoDescription=#{cargoDescription} where cargoID=#{cargoID} and isDel=FALSE")
    void update(int cargoID, String cargoName, String cargoDescription);

    @Update("update Cargo set isDel=TRUE where cargoID=#{cargoID} and isDel=FALSE")
    void delete(int cargoID);
}
