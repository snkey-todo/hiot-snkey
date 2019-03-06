package com.snkey.core.authorization.service.impl;


import com.snkey.core.authorization.model.TokenModel;
import com.snkey.core.authorization.service.TokenManager;
import com.snkey.core.config.Constant;
import com.snkey.core.entity.Result;
import com.snkey.core.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhusheng on 2019/3/5.
 *
 * @Component注解：
 */
@Component
public class TokenManagerImpl implements TokenManager {

    private StringRedisTemplate redis;

    @Autowired
    public void setRedis(StringRedisTemplate redis){
        this.redis = redis;
        redis.setKeySerializer(new StringRedisSerializer());
    }

    /**
     * 创建token
     * UUId为原始token，进行拼接，拼接后的token格式："UUId_tokenValue_typecode";
     * @param typeCode 指token类型，“use”表示用户token；“dev”表示设备token
     * @param UUId
     * @return
     */
    @Override
    public TokenModel createToken(String typeCode, String UUId) {
        //生成一个UUID字符串
        String value = UUID.randomUUID().toString().replace("_", "");
        String tokenValue = UUId + "_" + value + "_" + typeCode;
        TokenModel tokenModel = new TokenModel(UUId, typeCode, tokenValue);

        //存储到redis并设置过期时间
        if(typeCode.equalsIgnoreCase(Constant.AUTHORIZATION_USER)){
            redis.boundValueOps(UUId).set(tokenValue, Constant.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        }else if(typeCode.equalsIgnoreCase(Constant.AUTHORIZATION_DEVICE)){
           redis.boundValueOps(UUId).set(tokenValue, Constant.TOKEN_EXPIRES_DAY, TimeUnit.DAYS);
        }
        //返回生成的Token
        return tokenModel;
    }

    /**
     * 验证token是否有效
     * @param model
     * @return
     *
     * 判断用户持有的token和数据库的token是否一致，如果不一致表示token过时了，需要更新
     */
    @Override
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String tokenValue = redis.boundValueOps(model.getUUId()).get();
        if (tokenValue == null || !tokenValue.equals(model.getTokenValue())) {
            return false;
        }
        //如果验证成功，说明此用户（设备）进行了一次有效操作，延长token的过期时间
        if(model.getTypeCode().equalsIgnoreCase(Constant.AUTHORIZATION_USER)){
            redis.boundValueOps(model.getUUId()).expire(Constant.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        }else if(model.getTypeCode().equalsIgnoreCase(Constant.AUTHORIZATION_DEVICE)){
            redis.boundValueOps(model.getUUId()).expire(Constant.TOKEN_EXPIRES_DAY, TimeUnit.DAYS);
        }
        return true;
    }

    /**
     * 检验设备token是否有效
     * @param tokenModel
     * @return
     */
    @Override
    public Result checkToken2(TokenModel tokenModel) {
        //todo
        return null;
    }

    /**
     * 获取加密后的token中的信息
     * @param authentication  authentication/tokenValue = UUId + "_" + value + "_" + typeCode;
     * @return
     */
    @Override
    public TokenModel getToken(String authentication) {
        if(StringUtil.isEmpty(authentication)){
            return null;
        }
        String param[] = authentication.split("_");
        if(param.length != 3){
            return  null;
        }
        String uuid = param[0];
        String tokenValue = param[1];   //我们想要的初始token
        String typeCode = param[2];

        return new TokenModel(uuid, typeCode, authentication);
    }

    /**
     * 删除redis数据库中的token
     * @param UUId
     */
    @Override
    public void deleteToken(String UUId) {
        redis.delete(UUId);
    }
}
