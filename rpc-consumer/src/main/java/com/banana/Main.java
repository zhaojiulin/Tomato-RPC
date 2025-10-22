package com.banana;

import com.banana.api.UserService;
import com.tomato.rpc.model.Invocation;
import com.tomato.rpc.model.RemoteUrl;
import com.tomato.rpc.protocol.RpcClient;
import com.tomato.rpc.proxy.ProxyFactory;

public class Main {
    public static void main(String[] args) {

        Invocation invocation = new Invocation(
                UserService.class.getName(),
                "login",
                new Class[]{String.class, String.class},
                new Object[]{"用户名", "密码"}, "1.0.0");
        RpcClient rpcClient = new RpcClient();
        Object result = rpcClient.request("localhost", 8090, invocation);
        System.out.println(result.toString());

//        RemoteUrl remoteUrl = new RemoteUrl();
//        remoteUrl.setHost("localhost");
//        remoteUrl.setPort(8090);
//        UserService proxy = new ProxyFactory(remoteUrl).getProxy(UserService.class, "1.0.0");
//        String result = proxy.login("用户名", "密码");
//        System.out.println(result);

    }
}