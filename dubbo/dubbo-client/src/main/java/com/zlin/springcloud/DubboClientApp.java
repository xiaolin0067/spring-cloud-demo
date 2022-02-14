package com.zlin.springcloud;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zlin
 * @date 20220214
 */
@SpringBootApplication
@EnableDubbo
public class DubboClientApp {

    public static void main(String[] args) {
        SpringApplication.run(DubboClientApp.class, args);
    }

}
