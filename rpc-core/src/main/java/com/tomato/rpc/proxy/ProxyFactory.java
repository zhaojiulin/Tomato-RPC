package com.tomato.rpc.proxy;

import com.tomato.rpc.model.Invocation;
import com.tomato.rpc.loadbalance.SimpleLoadBalance;
import com.tomato.rpc.model.RemoteUrl;
import com.tomato.rpc.protocol.RpcClient;
import com.tomato.rpc.registry.RemoteUrlRegistry;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author zhaojiulin
 * @version 1.0
 * @description: 动态代理获取代理对象
 * @date 2025/10/22 19:27
 */
public class ProxyFactory {
    private final RemoteUrl remoteUrl;
    public ProxyFactory(RemoteUrl registry) {
        remoteUrl = registry;
    }
    public <T> T getProxy(Class<T> clazz, String version) {
        Object proxyInstance = Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), (proxy, method, args) -> {
            Invocation invocation = new Invocation(clazz.getName(), method.getName(), method.getParameterTypes(),
                    args, version);
            // 服务发现
            // List<RemoteUrl> remoteUrls = RemoteUrlRegistry.get(clazz.getName());
            // 负载均衡
            // RemoteUrl url = SimpleLoadBalance.random(remoteUrl);
            // 请求
            RpcClient rpcClient = new RpcClient();
            return rpcClient.request(remoteUrl.getHost(), remoteUrl.getPort(), invocation);
        });
        return (T) proxyInstance;
    }

}
