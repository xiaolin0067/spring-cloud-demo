package com.zlin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author zlin
 * @date 20211120
 */
@EnableHystrixDashboard
@SpringCloudApplication
public class DashboardApp {

    public static void main(String[] args) {
        SpringApplication.run(DashboardApp.class, args);
    }
}
