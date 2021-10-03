package com.zlin.springcloud.annotation;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author zlin
 * @date 20211003
 */
@Slf4j
@Aspect
@Component
public class AccessLimiterAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisScript<Boolean> redisScript;

    @Pointcut("@annotation(com.zlin.springcloud.annotation.AccessLimiter)")
    public void cut() {}

    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        // 获取切点的@AccessLimiter注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        AccessLimiter accessLimiter = method.getAnnotation(AccessLimiter.class);

        int limit = accessLimiter.limit();
        String key = accessLimiter.method();
        if (StringUtils.isEmpty(key)) {
            key = joinPoint.getTarget().getClass().getTypeName() + "@" + method.getName();
            Class[] methodParamTypes = method.getParameterTypes();
            if (methodParamTypes.length > 0) {
                String paramTypes = Arrays.stream(methodParamTypes)
                        .map(Class::getName)
                        .collect(Collectors.joining(","));
                key += "#" + paramTypes;
            }
            log.info("AccessLimiter key: {}", key);
        }

        // 调用redis
        boolean acquired = stringRedisTemplate.execute(
                // lua脚本
                redisScript,
                // lua脚本中的key列表
                Lists.newArrayList(key),
                // lua脚本中的value列表
                String.valueOf(limit)
        );
        // 使用debug的方式查看框架源码
        if (!acquired) {
            log.error("your access is blocked");
            throw new RuntimeException("your access is blocked");
        }
    }

}
