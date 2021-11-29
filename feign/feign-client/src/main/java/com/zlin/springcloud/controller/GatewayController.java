package com.zlin.springcloud.controller;

import com.zlin.springcloud.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zlin
 * @date 20211129
 */
@RestController
@Slf4j
@RequestMapping("gateway")
public class GatewayController {

    public static final Map<Long, Product> ITEMS = new ConcurrentHashMap<>();

    private final Object obj = new Object();

    @GetMapping("details")
    public Product get(@RequestParam("pid") Long pid) {
        if (!ITEMS.containsKey(pid)) {
            Product prod = Product.builder()
                    .productId(pid)
                    .description("好吃不贵")
                    .stock(100L)
                    .build();
            ITEMS.putIfAbsent(pid, prod);
        }
        return ITEMS.get(pid);
    }

    @PostMapping("placeOrder")
    public String buy(@RequestParam("pid") Long pid) {
        Product prod = ITEMS.get(pid);

        if (prod == null) {
            return "Product not found";
        } else if (prod.getStock() <= 0L) {
            return "Sold out";
        }

        synchronized (obj) {
            if (prod.getStock() <= 0L) {
                return "Sold out";
            }
            prod.setStock(prod.getStock() - 1);
        }

        return "Order Placed";
    }

}
