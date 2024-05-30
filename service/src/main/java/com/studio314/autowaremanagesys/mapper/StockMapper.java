package com.studio314.autowaremanagesys.mapper;

import com.studio314.autowaremanagesys.pojo.Stock;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StockMapper {

    @Select("select * from Stock where wareID = #{wID}")
    List<Stock> selectAllStocks(int wID);

    @Select("select * from Stock where wareID = #{wID} and cargoID = #{stockID}")
    Stock selectStock(int wID, int stockID);

    @Update("update Stock set num = #{num} where wareID = #{wID} and cargoID = #{stockID}")
    void updateStock(int wID, int stockID, int num);

    @Insert("insert into Stock (wareID, cargoID, num) values (#{wID}, #{stockID}, #{stockNum})")
    void insertStock(int wID, int stockID, int stockNum);
}
