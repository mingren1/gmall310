package com.athome.gmall.cartservice.service.impl;

import beans.CartInfo;
import com.alibaba.fastjson.JSON;
import com.athome.gmall.cartservice.constant.CartConst;
import com.athome.gmall.cartservice.mapper.CartInfoMapper;
import com.athome.gmall.cartservice.service.CartService;
import com.athome.gmall.serviceutil.config.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.List;

public class CartServiceImpl implements CartService {

    @Autowired
    private CartInfoMapper cartInfoMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void addToCart(String userId, String skuId, String skuNmu) {

        CartInfo cartInfo = new CartInfo();
        cartInfo.setSkuId(skuId);
        cartInfo.setUserId(userId);
//        CartInfo exitCartInfo = null;
        CartInfo o = (CartInfo) cartInfoMapper.selectOne(cartInfo);
        if (cartInfo != null) {
            cartInfo.setSkuNum(cartInfo.getSkuNum() + Integer.parseInt(skuNmu));
            int i = cartInfoMapper.updateByPrimaryKeySelective(cartInfo);
        } else {
            //新增购物车
            CartInfo cartInfo1 = new CartInfo();
            int i = cartInfoMapper.insertSelective(cartInfo1);
            cartInfo = cartInfo1;
        }

        //存入缓存中
        Jedis jedis = redisUtil.getJedis();
        String userCartKey = CartConst.USER_KEY_PREFIX + userId + CartConst.USER_CART_KEY_SUFFIX;
        jedis.hset(userCartKey, skuId, JSON.toJSONString(cartInfo));
        String userInfoKey = CartConst.USER_KEY_PREFIX + userId + CartConst.USER_KEY_SUFFIX;
        Long ttl = jedis.ttl(userInfoKey);
        jedis.expire(userCartKey, ttl.intValue());


    }

    @Override
    public List<CartInfo> mergeToCartList(List<CartInfo> cartListCK, String userId) {

        return null;
    }

    @Override
    public List<CartInfo> getCartList(String userId) {
        return null;
    }
}
