package com.banana.api;

/**
 * @author zhaojiulin
 * @version 1.0
 * @description: TODO
 * @date 2025/10/22 18:21
 */
public class UserServiceImpl implements UserService {
    @Override
    public String login(String username, String password) {
        return username+":"+password;
    }
}
