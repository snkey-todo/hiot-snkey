package com.snkey.core.authorization.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

/**
 * Created by zhusheng on 2019/3/5.
 */
public class TokenModel implements Serializable {
    //用户Id
    //@JsonPropertyOrder
    private String UUId;

    //token类型
    //序列化的时候忽略属性
    @JsonIgnore
    private String typeCode;

    //随机生成的token
    private String tokenValue;

    public TokenModel() {
    }

    public TokenModel(String UUId, String typeCode, String tokenValue) {
        this.UUId = UUId;
        this.typeCode = typeCode;
        this.tokenValue = tokenValue;
    }

    public String getUUId() {
        return UUId;
    }

    public void setUUId(String UUId) {
        this.UUId = UUId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    @Override
    public String toString() {
        return "TokenModel{" +
                "UUId='" + UUId + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", tokenValue='" + tokenValue + '\'' +
                '}';
    }
}
