package com.tomato.rpc.protocol;

import com.tomato.rpc.model.Invocation;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author zhaojiulin
 * @version 1.0
 * @description: 服务请求
 * @date 2025/10/22 18:11
 */
public class RpcClient {
    public Object request(String hostName, Integer port, Invocation invocation) {
        URL url = null;
        try {
            url = new URL("http", hostName, port, "/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            OutputStream outputStream = urlConnection.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(invocation);
            objectOutputStream.flush();
            objectOutputStream.close();

            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
