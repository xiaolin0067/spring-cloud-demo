package com.zlin.springcloud;

import com.zlin.springcloud.hystrix.Fallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by 半仙.
 */
@FeignClient(name = "feign-client", fallback = Fallback.class)
public interface MyService extends IService {

}
