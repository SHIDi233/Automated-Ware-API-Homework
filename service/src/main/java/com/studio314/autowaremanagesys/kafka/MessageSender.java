package com.studio314.autowaremanagesys.kafka;

import com.alibaba.fastjson2.JSON;
import com.studio314.autowaremanagesys.pojo.dto.OrderMsgDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageSender {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void addWaitMission(OrderMsgDTO mission) {
        //将m用fastjson转成json文本
        String message = JSON.toJSONString(mission);

        kafkaTemplate.send("wait", message);
    }

}
