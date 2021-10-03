package com.zlin.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zlin
 * @date 20211003
 */
@Slf4j
@RestController
public class NginxController {

    /**
     * nginx 专用
     */
    @GetMapping("/nginx")
    public String nginx() {
        log.info("Nginx success");
        return "success, time: " + System.currentTimeMillis();
    }

    /**
     * nginx 专用
     */
    @GetMapping("/nginx-conn")
    public String nginxConn(@RequestParam(defaultValue = "0") int secs) {
        log.info("Nginx conn success");
        try {
            Thread.sleep(secs * 1000);
        } catch (InterruptedException e) {
        }
        return "success, time: " + System.currentTimeMillis();
    }
}
