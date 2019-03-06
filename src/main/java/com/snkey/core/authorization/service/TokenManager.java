package com.snkey.core.authorization.service;


import com.snkey.core.authorization.model.TokenModel;
import com.snkey.core.entity.Result;

/**
 * Created by zhusheng on 2019/3/5.
 */
public interface TokenManager {
    /**
     * 1、创建token
     * 2、检查token是否有效,用户token，设备token
     * 3、获取加密后的token中的信息，如uuid， tokenType
     * 4、清除token
     */

    public TokenModel createToken(String typeCode, String UUId);

    public boolean checkToken(TokenModel tokenModel);

    public Result checkToken2(TokenModel tokenModel);

    public TokenModel getToken(String authentication);

    public void deleteToken(String UUId);

}
