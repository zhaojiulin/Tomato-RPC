package com.tomato.rpc.registry;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhaojiulin
 * @version 1.0
 * @description: 本地服务注册
 * @date 2025/10/22 17:58
 */
public class LocalRegistry {
    public static ConcurrentHashMap<String, Class<?>> interfaceMap = new ConcurrentHashMap<>();

    public static void register(String interfaceName, Class<?> interfaceClass) {
        register(interfaceName, interfaceClass, "1.0.0");
    }
    public static void  register(String interfaceName, Class<?> interfaceClass, String version) {
        interfaceMap.put(interfaceName + version, interfaceClass);
    }
    public static Class<?> get(String interfaceName) {
        return interfaceMap.get(interfaceName);
    }
    public static Class<?> get(String interfaceName, String version) {
        return interfaceMap.get(interfaceName + version);
    }
}
