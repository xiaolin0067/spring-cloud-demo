package com.zlin.demo;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D
 * @author zlin
 * @date 20211229
 */
public class SentinelDemo {

    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setCount(20);
        rule.setResource("控制流量的资源名");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);

        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    public static void main(String[] args) {
        initFlowRules();

        while (true) {
            try (Entry entry = SphU.entry("控制流量的资源名")) {
                // 待进行流量控制的代码
                System.out.println("我执行了！！");
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (BlockException e) {
                System.out.println("我被限制访问了！！");
            }
        }
    }

}
