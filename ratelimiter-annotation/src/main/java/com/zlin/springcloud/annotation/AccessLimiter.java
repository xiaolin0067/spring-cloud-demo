package com.zlin.springcloud.annotation;

import java.lang.annotation.*;

/**
 * @author zlin
 * @date 20211003
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimiter {

    int limit();

    String method() default "";

}
