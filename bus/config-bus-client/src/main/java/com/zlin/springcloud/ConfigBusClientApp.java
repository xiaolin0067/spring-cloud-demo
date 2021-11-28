package com.zlin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zlin
 * @date 20211128
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigBusClientApp {

    public static void main(String[] args) {
        SpringApplication.run(ConfigBusClientApp.class, args);
    }
}
