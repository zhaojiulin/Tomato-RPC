package com.tomato.rpc.loadbalance;

import com.tomato.rpc.model.RemoteUrl;

import java.util.List;
import java.util.Random;

/**
 * @author zhaojiulin
 * @version 1.0
 * @description: 简单负载均衡
 * @date 2025/10/22 19:48
 */
public class SimpleLoadBalance {

    public static RemoteUrl random(List<RemoteUrl> urls) {
        return urls.get(new Random().nextInt(urls.size()));
    }
}
