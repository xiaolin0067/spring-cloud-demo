package com.zlin.springcloud.controller;

import com.zlin.springcloud.AccessLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zlin
 * @date 20211003
 */
@Slf4j
@RestController
public class RateController {

    @Autowired
    private AccessLimiter accessLimiter;

    @GetMapping("/test")
    public String test() {
        accessLimiter.limitAccess("retelimiter-test", 1);
        return "success";
    }
}
