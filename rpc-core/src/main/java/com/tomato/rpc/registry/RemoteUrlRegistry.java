package com.tomato.rpc.registry;

import com.tomato.rpc.model.RemoteUrl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhaojiulin
 * @version 1.0
 * @description: 服务注册-伪代码
 * @date 2025/10/22 19:41
 */
public class RemoteUrlRegistry {
    public static ConcurrentHashMap<String, List<RemoteUrl>> remoteUrlMap = new ConcurrentHashMap<>();

    public static void  register(String interfaceName, RemoteUrl url) {
        List<RemoteUrl> remoteUrls = remoteUrlMap.get(interfaceName);
        if(remoteUrls == null) {
            remoteUrls = new ArrayList<>();
        }
        remoteUrls.add(url);
        remoteUrlMap.put(interfaceName, remoteUrls);
    }
    public static List<RemoteUrl> get(String interfaceName) {
        return remoteUrlMap.get(interfaceName);
    }
}
