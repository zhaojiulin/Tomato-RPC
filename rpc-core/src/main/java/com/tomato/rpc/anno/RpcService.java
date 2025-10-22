package com.tomato.rpc.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhaojiulin
 * @version 1.0
 * @description: TODO
 * @date 2025/10/22 19:30
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcService {
    String name() default "";
    String version() default "1.0.0";
}
