package com.zlin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zlin
 * @date 20211006
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerPeer2App {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerPeer2App.class, args);
    }

}
