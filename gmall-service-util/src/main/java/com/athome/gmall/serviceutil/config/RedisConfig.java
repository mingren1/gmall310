package com.athome.gmall.serviceutil.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host:disable}")
    private String host;
    @Value("${spring.redis.port}")
    private String port;
    @Value("${spring.redis.database}")
    private String database;

    public RedisUtil getRedisUtil() {
        if ("disable".equals(host)) {
            return null;
        }
        RedisUtil redisUtil = new RedisUtil();
        return redisUtil;
    }


}
