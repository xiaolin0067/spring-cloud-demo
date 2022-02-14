package com.zlin.springcloud.config;

import com.zlin.springcloud.filter.MyAuthFilter;
import com.zlin.springcloud.filter.TimerFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;

import java.time.ZonedDateTime;

/**
 * @author zlin
 * @date 20211129
 */
@DependsOn("myAuthFilter")
@Configuration
public class GatewayConfiguration {

    @Autowired
    private TimerFilter timerFilter;

    @Autowired
    private MyAuthFilter myAuthFilter;

    @Bean
    @Order
    public RouteLocator customizedRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/java/**")
                        .and().method(HttpMethod.GET)
                        .and().header("name")
                        .filters(f -> f.stripPrefix(1)
                                .addResponseHeader("java-param", "gateway-config")
                                .filter(timerFilter)
                                .filter(myAuthFilter)
                        )
                        .uri("lb://FEIGN-CLIENT")
                )
                .route(r -> r.path("/seckill/**")
                                .and().after(ZonedDateTime.now().plusMinutes(1))
//                        .and().before()
//                        .and().between()
                                .filters(f -> f.stripPrefix(1))
                                .uri("lb://FEIGN-CLIENT")
                )
                .build();

    }

}
