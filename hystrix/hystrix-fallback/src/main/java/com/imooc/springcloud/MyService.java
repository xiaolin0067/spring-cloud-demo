package com.imooc.springcloud;

import com.imooc.springcloud.hystrix.Fallback;
import com.zlin.springcloud.IService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by 半仙.
 */
@FeignClient(name = "feign-client", fallback = Fallback.class)
public interface MyService extends IService {

}
