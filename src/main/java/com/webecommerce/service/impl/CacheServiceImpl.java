package com.webecommerce.service.impl;

import com.webecommerce.service.ICacheService;
import com.webecommerce.utils.CacheFactory;
import redis.clients.jedis.Jedis;

public class CacheServiceImpl implements ICacheService {
    private Jedis jedis = CacheFactory.getConnection();
    @Override
    public void setKey(String key, String value, long ttlInSeconds) {
        jedis.set(key, value);
        if (ttlInSeconds > 0) {
            jedis.expire(key, ttlInSeconds);
        }
    }

    @Override
    public String getKey(String key) {
        return jedis.get(key);
    }

    @Override
    public long getTtlInSeconds(String key) {
        return jedis.ttl(key);
    }

    @Override
    public void increment(String key) {
        jedis.incr(key);
    }

    @Override
    public void delete(String key) {
        jedis.del(key);
    }
}
