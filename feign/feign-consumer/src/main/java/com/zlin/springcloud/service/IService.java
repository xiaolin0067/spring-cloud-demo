package com.zlin.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zlin
 * @date 20211010
 */
@FeignClient("eureka-client")
public interface IService {

    @GetMapping("/seyHi")
    String seyHi();

}
