package com.tomato.rpc.protocol;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author zhaojiulin
 * @version 1.0
 * @description: 请求处理
 * @date 2025/10/22 17:48
 */
public class RpeDispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new RpcServerHandle().handle(req, resp);
    }
}
