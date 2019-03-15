package com.athome.gmall.orderweb.order;

import beans.OrderDetail;
import beans.OrderInfo;
import beans.UserAddress;
import com.athome.gmall.orderservice.constant.OrderStatus;
import com.athome.gmall.orderservice.service.OrderService;
import com.athome.gmall.usermanagerservice.service.UserManagerService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.ibatis.annotations.ResultType;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class OrderController {

    @Reference
    private UserManagerService userManagerService;
    @Reference
    private OrderService orderService;

    @RequestMapping("trade")
    public String trade(HttpServletRequest request, HttpServletResponse response) {

        String userId = (String) request.getAttribute("userId");
        List<UserAddress> addressList = userManagerService.getUserAddressList(userId);
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderDetailList(orderDetails);
        orderInfo.sumTotalAmount();
        request.setAttribute("totalAmount",orderInfo.getTotalAmount());
        request.setAttribute("addressList",addressList);
        request.setAttribute("orderDetailList",orderDetails);
        request.setAttribute("tradeNo",orderService.getTradeNo(userId));

        return "trade";
    }

    @RequestMapping("submitOrder")
    public String submitOrder(HttpServletRequest request,OrderInfo orderInfo) {

        String userId =  (String) request.getAttribute("userId");
        String tradeNo = request.getParameter("tradeNo");
        boolean flag = orderService.checkOrderTradeNO(userId, tradeNo);
        if (!flag) {
            request.setAttribute("errMsg", "页面已经失效");
            return "fail";
        }
        List<OrderDetail> orderDetailList = orderInfo.getOrderDetailList();
        if (orderDetailList != null && orderDetailList.size() > 0) {
            for (OrderDetail orderDetail : orderDetailList) {
               boolean result = orderService.checkStock(orderDetail.getSkuId(), orderDetail.getSkuNum());
                if (!result) {
                    request.setAttribute("errMsg", "库存不足");
                    return "fail";
                }
            }
        }

        //跳转到支付页面
//        orderInfo.setOrderStatus(OrderStatus.PAID);

        String orderId = orderService.saveOrder(orderInfo);
        orderService.deleteTradeCode(userId);
        return "redirect://payment.gmall.com/index?orderId="+orderId;

    }
}
