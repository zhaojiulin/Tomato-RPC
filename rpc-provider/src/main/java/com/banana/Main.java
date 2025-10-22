package com.banana;

import com.banana.api.UserService;
import com.banana.api.UserServiceImpl;
import com.tomato.rpc.model.RemoteUrl;
import com.tomato.rpc.protocol.RpcServer;
import com.tomato.rpc.registry.LocalRegistry;
import com.tomato.rpc.registry.RemoteUrlRegistry;

public class Main {
    public static void main(String[] args) {
        // 本地注册
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 服务注册
//        RemoteUrl remoteUrl = new RemoteUrl();
//        remoteUrl.setHost("localhost");
//        remoteUrl.setPort(8090);
//        RemoteUrlRegistry.register(UserService.class.getName(), remoteUrl);
        // rpc端口启动
        new RpcServer().start("localhost", 8090);
    }
}