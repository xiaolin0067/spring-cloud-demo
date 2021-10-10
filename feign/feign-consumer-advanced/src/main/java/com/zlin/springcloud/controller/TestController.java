package com.zlin.springcloud.controller;

import com.zlin.springcloud.IService;
import com.zlin.springcloud.entity.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zlin
 * @date 20211010
 */
@RestController
public class TestController {

    @Autowired
    private IService service;

    @GetMapping("/seyHi")
    public String seyHi() {
        return service.seyHi();
    }

    @GetMapping("/seyHi2")
    public Friend seyHi2() {
        return service.test(new Friend("FeignConsumerAdvancedApp"));
    }
}
