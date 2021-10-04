package com.zlin.springcloud.controller;

import com.zlin.springcloud.entity.Friend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zlin
 * @date 20211004
 */
@Slf4j
@RestController
public class TestController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/seyHi")
    public String seyHi() {
        return "This is " + port;
    }

    @PostMapping("/seyHi")
    public Friend test(@RequestBody Friend friend) {
        log.info("you are {}", friend);
        friend.setPort(port);
        return friend;
    }

}
