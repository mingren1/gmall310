package com.athome.gmall.orderservice.task;

import beans.OrderInfo;
import com.athome.gmall.orderservice.service.OrderService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@EnableScheduling
@Component
public class OrderTask {

    @Reference
    private OrderService orderService;

    @Scheduled(cron = "0/30 * * * * *")
    public void chechOrder() {

        List<OrderInfo> expiredOrders = orderService.getExpiredOrderList();
        for (OrderInfo expiredOrder : expiredOrders) {
            orderService.execExpiredOrderList(expiredOrders);
        }
    }
}
