package com.it.itoken.web.admin.service.fallback;

import com.it.itoken.web.admin.service.RedisService;
import org.springframework.stereotype.Component;

/**
 * redis服务熔断器
 *
 * @author wjh
 * @create 2019-09-28 21:20
 */
@Component
public class RedisServiceFallback implements RedisService {
    @Override
    public String put(String key, String value, long seconds) {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }
}
