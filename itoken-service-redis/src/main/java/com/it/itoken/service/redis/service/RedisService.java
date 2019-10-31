package com.it.itoken.service.redis.service;

/**
 * @author wjh
 * @create 2019-09-28 19:44
 */
public interface RedisService {
    /**
     * 对redis的存数据
     * @param key
     * @param value
     * @param seconds 超时时间
     */
    void put(String key, Object value, long seconds);

    /**
     * 获取数据
     * @param key
     */
    Object get(String key);
}
