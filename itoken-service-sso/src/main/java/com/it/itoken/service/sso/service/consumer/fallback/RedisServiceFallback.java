package com.it.itoken.service.sso.service.consumer.fallback;

import com.google.common.collect.Lists;
import com.it.itoken.common.constants.HttpStatusConstants;
import com.it.itoken.common.dto.BaseResult;
import com.it.itoken.common.hystrix.Fallback;
import com.it.itoken.common.util.MapperUtils;
import com.it.itoken.service.sso.service.consumer.RedisService;
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
