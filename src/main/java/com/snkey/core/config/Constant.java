package com.snkey.core.config;


/**
 * Created by zhusheng on 2019/3/6.
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
     * 用户token有效期（小时），为了方便测试，我们将失效时间设置的很长，在实际应用中，我们可以更具实际需求来设置失效时间
     */
    public static final int TOKEN_EXPIRES_HOUR = 2160; //90天
    /**
     * 设备token有效期（天）
     */
    public static final int TOKEN_EXPIRES_DAY = 180; //180天



    /**
     * 登录客户端标识:WEB端:W
     */
    public static final String CLIENTCODE_WEB = "W";
    /**
     * 登录客户端标识:APP端:A
     */
    public static final String CLIENTCODE_APP = "A";
    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";
    /**
     * 存储当前设备id的字段名
     */
    public static final String CURRENT_DEVICE_ID = "CURRENT_DEVICE_ID";
    /**
     * 存储当前登录用户登录的客户端类型：WEB或者APP
     */
    public static final String CURRENT_USER_CLIENTCODE = "CURRENT_USER_CLIENTCODE";
}
