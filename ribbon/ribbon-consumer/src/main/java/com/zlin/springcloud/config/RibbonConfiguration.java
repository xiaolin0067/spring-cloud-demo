package com.zlin.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zlin
 * @date 20211008
 */
@Configuration
@RibbonClient(name = "eureka-client", configuration = com.zlin.springcloud.rules.MyConsistentHashingRule.class)
public class RibbonConfiguration {

    /**
     * 修改全局负载均衡策略
     */
//    @Bean
//    public IRule defaultLoadBalancedStrategy() {
//        return new RandomRule();
//    }

}
