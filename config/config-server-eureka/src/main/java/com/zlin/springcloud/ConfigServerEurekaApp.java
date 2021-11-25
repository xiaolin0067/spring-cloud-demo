package com.zlin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author zlin
 * @date 20211124
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigServerEurekaApp {

//    获取配置文件的不同URL姿势，都是GET请求
//    http://localhost:60000/{label}/{application}-{profile}.json
//    以上后缀可以换成.yml, .properties，如果不指定{label}的话默认用master
//
//    http://localhost:60000/{application}/{profile}/{label}
//    如果不指定{label}的话默认用master

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerEurekaApp.class, args);
    }
}
