package com.studio314.autowaremanagesys.interceptor;

import java.lang.annotation.*;

/**
 * @author toutou
 * @date by 2020/12
 * @des 限流注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limiting {

    // 默认每秒放入桶中的token
    double limitNum() default 1;

    String name() default "";
}