package com.zlin;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zlin
 * @date 20211229
 */
@SpringBootApplication
public class SentinelApp {

    public static void main(String[] args) {
        SpringApplication.run(SentinelApp.class, args);
        initFlowRules();
    }

    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setCount(5);
        rule.setResource("控制流量的资源名");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);

        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

}
