package com.athome.gmall.cartservice.service;

import beans.CartInfo;
import beans.UserInfo;

import java.util.List;

public interface CartService {

    public void addToCart(String userId,String skuId,String skuNmu);

    List<CartInfo> mergeToCartList(List<CartInfo> cartListCK, String userId);

    List<CartInfo> getCartList(String userId);
}
