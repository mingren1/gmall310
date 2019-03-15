package com.athome.gmall.cartweb.controller;

import beans.CartInfo;
import com.athome.gmall.cartservice.service.CartService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.CookieHandler;
import java.util.List;

@Controller
public class CartController {

    @Reference
    private CartService cartService;
    @Autowired
    private CartCookieHandler cartCookieHandler;

    @RequestMapping("addToCart")
    public String addToCart(HttpServletRequest request, HttpServletResponse response) {

        String skuNum = request.getParameter("skuNum");
        String skuId = request.getParameter("skuId");
        String userId = (String)request.getAttribute("userId");

        if (userId != null) {
            cartService.addToCart(userId, skuId, skuNum);
        } else {
            CartCookieHandler.addToCart(request, response, userId, skuId, Integer.parseInt(skuNum));
        }
        return "success";
    }

    @RequestMapping("cartList")
    public String cartList(HttpServletRequest request, HttpServletResponse response) {
        String userId = (String) request.getAttribute("userId");
        List<CartInfo> cartInfoList = null;
        if (userId != null) {
            //从cookie中查找，并合并到到redis中，然后删除cookie中的数据
           List<CartInfo> cartListCK = cartCookieHandler.getCartList(request);
            if (cartListCK != null && cartListCK.size() > 0) {
                cartInfoList = cartService.mergeToCartList(cartListCK, userId);
                cartCookieHandler.deleteCartCookie(request, response);
            } else {
                //从redis中获取数据
                cartInfoList = cartService.getCartList(userId);
                request.setAttribute("cartInfoList",cartInfoList);
            }

        }
        return "cartList";
    }

    public String toTrade(HttpServletRequest request, HttpServletResponse response) {
        //选中的商品确定cookie中是否还有此商品
        List<CartInfo> cartList = cartCookieHandler.getCartList(request);
        String userId = (String) request.getAttribute("userId");
        if (cartList != null && cartList.size()>0) {
            List<CartInfo> cartInfoList = cartService.mergeToCartList(cartList, userId);
            //删除cookie中的列表
            cartCookieHandler.deleteCartCookie(request,response);
        }
        return "redirect://order.gmall.com/trade";
    }
}
