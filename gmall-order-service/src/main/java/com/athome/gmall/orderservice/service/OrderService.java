package com.athome.gmall.orderservice.service;

import beans.OrderInfo;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface OrderService {
    Object getTradeNo(String userId);

    boolean checkOrderTradeNO(String userId, String tradeNo);

    String saveOrder(OrderInfo orderInfo);

    void deleteTradeCode(String userId);

    @Async
    void execExpiredOrderList(List<OrderInfo> expiredOrders);

    List<OrderInfo> getExpiredOrderList();

    boolean checkStock(String skuId, Integer skuNum);
}
