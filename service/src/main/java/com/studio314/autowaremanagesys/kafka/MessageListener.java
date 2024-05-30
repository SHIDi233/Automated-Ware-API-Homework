package com.studio314.autowaremanagesys.kafka;

import com.alibaba.fastjson2.JSON;
import com.studio314.autowaremanagesys.pojo.Order;
import com.studio314.autowaremanagesys.pojo.dto.OrderMsgDTO;
import com.studio314.autowaremanagesys.service.OrderService;
import com.studio314.autowaremanagesys.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageListener {

    @Autowired
    StockService stockService;

    @Autowired
    OrderService orderService;

    @KafkaListener(topics = "accomplish", groupId = "testGroup")
    public void listenAccomplish(String message) {
        System.out.println("Received Accomplish message: " + message);
        OrderMsgDTO m = JSON.parseObject(message, OrderMsgDTO.class);
        if (m.getType() == 1) {
            stockService.createStock(m.getWareID(), m.getCargoID(), m.getNum());
            orderService.modifyOrder(m.getNumber(), Order.OrderState.IN_FINISHED.getStateNum());
        } else if (m.getType() == 2) {
            orderService.modifyOrder(m.getNumber(), Order.OrderState.OUT_FINISHED.getStateNum());
        }

    }

    @KafkaListener(topics = "start", groupId = "testGroup")
    public void listenStart(String message) {
        System.out.println("Received Start message: " + message);
        OrderMsgDTO m = JSON.parseObject(message, OrderMsgDTO.class);
        if (m.getType() == 1) {
            orderService.modifyOrder(m.getNumber(), Order.OrderState.IN_PROCESSING.getStateNum());
        } else if (m.getType() == 2) {
            stockService.outStock(m.getWareID(), m.getCargoID(), m.getNum());
            orderService.modifyOrder(m.getNumber(), Order.OrderState.OUT_PROCESSING.getStateNum());
        }
    }

}
