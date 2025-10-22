package com.tomato.rpc.model;

/**
 * @author zhaojiulin
 * @version 1.0
 * @description: 服务提供者信息
 * @date 2025/10/22 19:42
 */
public class RemoteUrl {
    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
