package com.tomato.protocol;

import com.tomato.common.Invocation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import registry.LocalRegistry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhaojiulin
 * @version 1.0
 * @description: TODO
 * @date 2025/10/22 17:39
 */
public class RpcServerHandle {
    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
            String interfaceName = invocation.getInterfaceName();
            Class<?> clazz = LocalRegistry.get(interfaceName, invocation.getVersion());
            Method method = clazz.getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            Object invoke = method.invoke(clazz.getDeclaredConstructor().newInstance(), invocation.getParameters());
            sendJsonResponse(resp, 200, invoke.toString());
        } catch (IOException | InstantiationException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendJsonResponse(HttpServletResponse resp, int status, String json) {
        resp.setStatus(status);
        resp.setContentType("application/json;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = null;
        try {
            out = resp.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!json.isEmpty()) {
            out.print(json);
        }
        out.flush();
    }
}
