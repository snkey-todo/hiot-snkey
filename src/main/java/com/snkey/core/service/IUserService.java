package com.snkey.core.service;


import com.snkey.core.entity.Result;
import com.snkey.core.entity.User;

/**
 * Service层是处理业务逻辑的
 */
public interface IUserService {
    /**
     * 1、注册
     * 2、登录
     */
    public int register(User user);

    public Result login(User user);

}
