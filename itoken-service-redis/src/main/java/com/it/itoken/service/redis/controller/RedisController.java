package com.it.itoken.service.redis.controller;

import com.it.itoken.service.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wjh
 * @create 2019-09-28 19:51
 */
@RestController
public class RedisController {
    @Autowired
    private RedisService redisService;

    @PostMapping("/put")
    public String put(String key,String value,long seconds){
        redisService.put(key,value,seconds);
        return "ok";
    }

    @GetMapping("/get")
    public String get(String key){
        Object obj = redisService.get(key);
        if(obj != null){
            String json = String.valueOf(obj);
            return json;
        }
        return null;
    }

}
