package com.studio314.autowaremanagesys.service;

import com.studio314.autowaremanagesys.utils.Result;

public interface OrderService {

    Result createInOrder(int wID, int cargoID, int stockNum);

    Result createOutOrder(int wID, int cargoID, int stockNum);

    Result listOrders(int wID);

    Result query(String orderNumber);

    int modifyOrder(String orderNumber, int state);
}
