package com.zlin.springcloud.controller;

import com.zlin.springcloud.IService;
import com.zlin.springcloud.entity.Friend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zlin
 * @date 20211010
 */
@Slf4j
@RestController
public class TestController implements IService {

    @Value("${server.port}")
    private String port;

    @Override
    public String seyHi() {
        return "This is " + port;
    }

    @Override
    public Friend test(Friend friend) {
        log.info("you are {}", friend);
        friend.setPort(port);
        return friend;
    }

}
