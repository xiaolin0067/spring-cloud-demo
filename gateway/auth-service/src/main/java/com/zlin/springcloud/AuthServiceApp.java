package com.zlin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zlin
 * @date 20211129
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AuthServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApp.class, args);
    }
}