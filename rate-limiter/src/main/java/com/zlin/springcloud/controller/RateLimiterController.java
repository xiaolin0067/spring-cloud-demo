package com.zlin.springcloud.controller;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zlin
 * @date 20211003
 */
@Slf4j
@RestController
public class RateLimiterController {

    /**
     * 每秒发放两个通行证
     * 非分布式限流器，对于访问其他中组件的限流不起作用，仅可用于当前服务较为消耗资源的地方的限流
     */
    RateLimiter limiter = RateLimiter.create(2.0);

    /**
     * 非阻塞限流，拿不到令牌立刻返回失败
     * 开始请求存在预热的情况，即刚开始可以获得令牌，后面需要记账限流
     * 若请求需要的令牌数大于当前有的令牌，那么可以返回成功，即令牌数可以为负数，为负数之后的请求立刻返回失败
     * @param count 一次请求需要消耗的令牌
     */
    @GetMapping("/tryAcquire")
    public String tryAcquire(Integer count) {
        if (count == null) {
            count = 1;
        }
        if (limiter.tryAcquire(count)) {
            log.info("success, rate is {}", limiter.getRate());
            return "success";
        }else {
            log.info("fail, rate is {}", limiter.getRate());
            return "fail";
        }
    }

    /**
     * 限定时间的非阻塞限流，给定一个超时时间，若拿不到令牌不立刻返回失败，会等待到该时间
     * RateLimiter会计算该超时时间内能否获得需求令牌数，若拿不到则不会阻塞立刻返回失败，否则阻塞等待令牌数并返回成功
     * @param count 一次请求需要消耗的令牌
     */
    @GetMapping("/tryAcquireWithTimeout")
    public String tryAcquireWithTimeout(Integer count, Integer timeout) {
        if (count == null || count < 1) {
            count = 1;
        }
        if (timeout == null || timeout < 0) {
            timeout = 0;
        }
        if (limiter.tryAcquire(count, timeout, TimeUnit.SECONDS)) {
            log.info("success, rate is {}", limiter.getRate());
            return "success";
        }else {
            log.info("fail, rate is {}", limiter.getRate());
            return "fail";
        }
    }

    /**
     * 阻塞式限流，若拿不到令牌会一直等待直到获取到令牌
     */
    @GetMapping("/acquire")
    public String acquire(Integer count) {
        if (count == null) {
            count = 1;
        }
        limiter.acquire(count);
        log.info("success, rate is {}", limiter.getRate());
        return "success";
    }

}
