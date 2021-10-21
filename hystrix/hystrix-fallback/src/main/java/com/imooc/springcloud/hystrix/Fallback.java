package com.imooc.springcloud.hystrix;

import com.imooc.springcloud.MyService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zlin.springcloud.entity.Friend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by 半仙.
 */
@Slf4j
@Component
public class Fallback implements MyService {

    @Override
    public String error() {
        log.info("Fallback: I'm not a black sheep any more");
        return "Fallback: I'm not a black sheep any more";
    }

    @HystrixCommand(fallbackMethod = "fallback3")
    public String fallback2() {
        log.info("fallback again");
        throw new RuntimeException("fallback again");
    }

    public String fallback3() {
        log.info("fallback again and again");
        return "success";
    }

    @Override
    public String seyHi() {
        return null;
    }

    @Override
    public Friend test(Friend friend) {
        return null;
    }

    @Override
    public String retry(Integer second) {
        return "You are late !";
    }

}
