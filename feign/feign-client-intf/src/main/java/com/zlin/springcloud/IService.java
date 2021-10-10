package com.zlin.springcloud;

import com.zlin.springcloud.entity.Friend;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zlin
 * @date 20211010
 */
@FeignClient("feign-client")
public interface IService {

    @GetMapping("/seyHi")
    String seyHi();

    @PostMapping("/seyHi")
    Friend test(@RequestBody Friend friend);

    @GetMapping("/retry")
    String retry(@RequestParam("second") Integer second);

}
