package com.zlin.springcloud.controller;

import com.zlin.springcloud.service.IService;
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

}
