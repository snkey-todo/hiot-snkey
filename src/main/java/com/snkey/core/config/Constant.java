package com.snkey.core.config;


import java.util.concurrent.TimeUnit;

/**
 * Created by zhusheng on 2019/3/5.
 */
public class Constant {
    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "Authorization";
    /**
     * Authorization(token)的类型:用户类型
     */
    public static final String AUTHORIZATION_USER = "user";
    /**
     * Authorization(token)的类型:设备类型
     */
    public static final String AUTHORIZATION_DEVICE = "dev";

    /**
     * 用户token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 2160; //90天
    /**
     * 设备token有效期（天）
     */
    public static final int TOKEN_EXPIRES_DAY = 180; //180天

}
