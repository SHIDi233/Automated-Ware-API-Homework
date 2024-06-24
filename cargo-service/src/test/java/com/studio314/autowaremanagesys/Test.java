package com.studio314.autowaremanagesys;

import java.util.UUID;

public class Test {

    public static void main(String[] args) {
        String orderNumber = "IN-" + (System.currentTimeMillis() % 1000000000) + UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        System.out.println(orderNumber);
    }

}
