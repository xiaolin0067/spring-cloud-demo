package com.zlin.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zlin
 * @date 20211004
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friend {

    private String name;
    private String port;

    public Friend(String feignConsumerAdvancedApp) {
        this.name = feignConsumerAdvancedApp;
    }
}
