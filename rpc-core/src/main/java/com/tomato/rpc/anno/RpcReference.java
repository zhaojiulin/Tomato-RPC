package com.tomato.rpc.anno;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RpcReference {
    /** 远程服务接口类 */
    Class<?> interfaceClass();
    /** 版本号（与服务端保持一致） */
    String version() default "";
}
