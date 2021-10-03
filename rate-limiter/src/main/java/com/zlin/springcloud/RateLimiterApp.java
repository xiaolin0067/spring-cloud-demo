package com.zlin.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author zlin
 * @date 20211003
 */
@SpringBootApplication
public class RateLimiterApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(RateLimiterApp.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
