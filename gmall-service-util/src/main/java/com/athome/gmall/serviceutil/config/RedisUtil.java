package com.athome.gmall.serviceutil.config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

    private JedisPool jedisPool;

    public void initJedisPool(String host,String port,String database) {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxWaitMillis(2000L);
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMinIdle(10);
        jedisPool = new JedisPool(jedisPoolConfig,host,Integer.parseInt(port),20*1000);
    }

    public Jedis getJedis() {
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }
}
