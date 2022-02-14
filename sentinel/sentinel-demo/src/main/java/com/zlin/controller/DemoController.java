package com.zlin.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zlin
 * @date 20211229
 */
@Slf4j
@RestController
public class DemoController {

    @GetMapping("/flow")
    public String flow() {
        try (Entry entry = SphU.entry("控制流量的资源名")) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("我执行了！！");
            return "我执行了！！";
        } catch (BlockException e) {
            log.error("我被限制访问了！！");
            return "我被限制访问了！！";
        }

    }

}
