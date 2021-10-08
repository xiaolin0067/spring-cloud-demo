package com.zlin.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zlin
 * @date 20211008
 */
@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/seyHi")
    public String seyHi() {
        return restTemplate.getForObject("http://eureka-client/seyHi", String.class);
    }

}
