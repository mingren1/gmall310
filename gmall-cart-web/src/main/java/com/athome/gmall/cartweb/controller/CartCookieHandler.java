package com.athome.gmall.cartweb.controller;

import beans.CartInfo;
import com.alibaba.fastjson.JSON;
import com.athome.gmall.webutil.util.CookieUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.List;

@Component
public class CartCookieHandler {


    private static String cookieCartName = "CART";
//    private String cookieCartName = "CART";

    public static void addToCart(HttpServletRequest request, HttpServletResponse response, String userId, String skuId, int i) {

        String cookieValue = CookieUtil.getCookieValue(request, cookieCartName, true);
    }

    public List<CartInfo> getCartList(HttpServletRequest request) {
        String cookieValue = CookieUtil.getCookieValue(request, cookieCartName, true);
        if (cookieValue != null && cookieValue.length() > 0) {
            List<CartInfo> arrays = JSON.parseArray(cookieValue, CartInfo.class);
            return arrays;
        }
        return null;
    }

    public void deleteCartCookie(HttpServletRequest request, HttpServletResponse response) {

    }
}
