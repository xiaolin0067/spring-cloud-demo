package com.zlin.springcloud;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

/**
 * @author zlin
 * @date 20211003
 */
@Slf4j
@Service
public class AccessLimiter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisScript<Boolean> redisScript;

    public void limitAccess(String key, Integer limit) {

        boolean acquired = stringRedisTemplate.execute(
                redisScript,                // lua脚本
                Lists.newArrayList(key),    // lua脚本中的key列表
                limit.toString()            // lua脚本中的value列表
        );
        // 使用debug的方式查看框架源码
        if (!acquired) {
            log.error("your access is blocked");
            throw new RuntimeException("your access is blocked");
        }

    }

}
