package com.zlin.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zlin
 * @date 20211004
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaServerApp.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
