package com.studio314.autowaremanagesys.service.impl;

import com.studio314.autowaremanagesys.kafka.MessageSender;
import com.studio314.autowaremanagesys.mapper.CargoMapper;
import com.studio314.autowaremanagesys.mapper.OrderMapper;
import com.studio314.autowaremanagesys.mapper.StockMapper;
import com.studio314.autowaremanagesys.pojo.Cargo;
import com.studio314.autowaremanagesys.pojo.Order;
import com.studio314.autowaremanagesys.pojo.Stock;
import com.studio314.autowaremanagesys.pojo.dto.OrderMsgDTO;
import com.studio314.autowaremanagesys.service.OrderService;
import com.studio314.autowaremanagesys.service.StockService;
import com.studio314.autowaremanagesys.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    MessageSender messageSender;

    @Autowired
    StockService stockService;

    @Autowired
    CargoMapper cargoMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    StockMapper stockMapper;

    @Override
    public Result createInOrder(int wID, int cargoID, int stockNum) {
        if (stockNum <= 0) {
            return Result.error("数量必须大于0");
        }

        Cargo cargo = cargoMapper.select(cargoID);
        if(cargo == null){
            return Result.error("货物不存在");
        }

        //生成32位订单号
        String orderNumber = "IN-" + (System.currentTimeMillis() % 1000000000) +
                UUID.randomUUID().toString().replace("-", "").substring(0, 10);


        //将入库任务提交到等待队列
        OrderMsgDTO msg = new OrderMsgDTO(orderNumber, 1, wID, cargoID, stockNum);
        orderMapper.insertOrder(msg, Order.OrderState.IN_WAITING.getStateNum());
        messageSender.addWaitMission(msg);

        return Result.success();
    }

    @Override
    public Result createOutOrder(int wID, int cargoID, int stockNum) {
        if (stockNum <= 0) {
            return Result.error("数量必须大于0");
        }

        Stock stock = stockMapper.selectStock(wID, cargoID);
        if(stock == null){
            return Result.error("货物不存在");
        }

        if(stock.getNum() < stockNum){
            return Result.error("库存不足");
        }

        //生成32位订单号
        String orderNumber = "OT-" + (System.currentTimeMillis() % 1000000000) +
                UUID.randomUUID().toString().replace("-", "").substring(0, 10);


        //先修改库存再将出库任务提交到等待队列
        stockService.outStock(wID, cargoID, stockNum);
        OrderMsgDTO msg = new OrderMsgDTO(orderNumber, 2, wID, cargoID, stockNum);
        orderMapper.insertOrder(msg, Order.OrderState.OUT_WAITING.getStateNum());
        messageSender.addWaitMission(msg);

        return Result.success();
    }

    @Override
    public Result listOrders(int wID) {
        List<Order> orders = orderMapper.selectByWID(wID);
        return Result.success(orders);
    }

    @Override
    public Result query(String orderNumber) {
        Order order = orderMapper.selectByNumber(orderNumber);
        return Result.success(order);
    }

    @Override
    public int modifyOrder(String orderNumber, int state) {
        orderMapper.modifyState(orderNumber, state);
        return 0;
    }
}
