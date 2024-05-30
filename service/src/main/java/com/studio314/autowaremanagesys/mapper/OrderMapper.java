package com.studio314.autowaremanagesys.mapper;

import com.studio314.autowaremanagesys.pojo.Order;
import com.studio314.autowaremanagesys.pojo.dto.OrderMsgDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Insert("insert into orders (number, type, wareID, cargoID, num, state) values (#{number}, #{type}, #{wareID}, #{cargoID}, #{num}, #{state})")
    void insertOrder(OrderMsgDTO orderMsgDTO, int state);

    @Select("select * from orders where wareID = #{wID}")
    List<Order> selectByWID(int wID);

    @Select("select * from orders where number = #{orderNumber}")
    Order selectByNumber(String orderNumber);

    @Update("update orders set state = #{state} where number = #{orderNumber}")
    void modifyState(String orderNumber, int state);
}
