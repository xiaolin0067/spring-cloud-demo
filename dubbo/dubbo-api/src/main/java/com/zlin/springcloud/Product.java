package com.zlin.springcloud;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zlin
 * @date 20220214
 */
@Data
public class Product implements Serializable {

    private String name;
    private BigDecimal price;

}