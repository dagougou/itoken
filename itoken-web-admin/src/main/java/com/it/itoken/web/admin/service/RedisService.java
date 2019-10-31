package com.it.itoken.web.admin.service;

import com.it.itoken.web.admin.service.fallback.RedisServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author wjh
 * @create 2019-09-28 21:16
 */
@FeignClient(value = "itoken-service-redis", fallback = RedisServiceFallback.class)
public interface RedisService {
    /**
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    @PostMapping("/put")
    String put(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("seconds") long seconds);

    /**
     * @param key
     * @return
     */
    @GetMapping("/get")
    String get(@RequestParam("key") String key);
}


