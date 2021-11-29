package com.zlin.springcloud.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author zlin
 * @date 20211129
 */
@Data
@Builder
public class Product {

    private Long productId;

    private String description;

    private Long stock;

}
