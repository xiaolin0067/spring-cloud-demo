package com.zlin.springcloud.controller;

import com.zlin.springcloud.annotation.AccessLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zlin
 * @date 20211003
 */
@Slf4j
@RestController
public class AnnoController {

    @AccessLimiter(limit = 1)
    @GetMapping("/test-anno")
    public String test() {
        return "success";
    }
}
