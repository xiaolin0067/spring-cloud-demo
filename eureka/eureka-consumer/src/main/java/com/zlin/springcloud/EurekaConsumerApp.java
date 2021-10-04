package com.zlin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author zlin
 * @date 20211004
 */
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaConsumerApp {

    @Bean
    public RestTemplate register() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerApp.class, args);
    }

}
