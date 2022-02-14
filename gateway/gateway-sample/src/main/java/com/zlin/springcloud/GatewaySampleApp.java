package com.zlin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zlpang
 * @date 20211129
 */
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class GatewaySampleApp {

    public static void main(String[] args) {
        SpringApplication.run(GatewaySampleApp.class, args);
    }
}
