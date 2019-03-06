package com.snkey.core.authorization.service.impl;

import com.snkey.core.authorization.model.TokenModel;
import com.snkey.core.authorization.service.TokenManager;
import com.snkey.core.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.junit.Assert.*;


/**
 * Created by zhusheng on 2019/3/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TokenManagerImplTest {

    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private UserDao userDao;



    @Test
    public void checkLoginCount() throws Exception {
    }

    /**
     * 创建token
     * @throws Exception
     */
    @Test
    public void createToken() throws Exception {
        String uuid = UUID.randomUUID().toString().replace("_", "");
        String typeCode = "user";;
        TokenModel tokenModel  = tokenManager.createToken(typeCode, uuid);
        System.out.println(tokenModel);
    }

    /**
     * 检查token是否有效
     *
     * @throws Exception
     */
    @Test
    public void checkToken() throws Exception {
        //获取一个已有的TokenModle,判断是过期
        TokenModel tokenModel = new TokenModel("d523fb34-0ebd-4f41-9b1b-092c84ebc2f4", "user", "d523fb34-0ebd-4f41-9b1b-092c84ebc2f4_f5234f27-f73d-4c88-8d6e-17870fbe65da_user");

        //检查
        Boolean result = tokenManager.checkToken(tokenModel);
        System.out.println("检查结果:" + result);

    }

    @Test
    public void getToken() throws Exception {
        String authorization = "9561f578-dd4e-4333-8ccc-8f64fb6e7819_user_ad19583a-a10a-46f5-9b25-4e236b693af7";
        TokenModel tokenModel = tokenManager.getToken(authorization);
        System.out.println(tokenModel);
    }

    @Test
    public void deleteToken() throws Exception {

    }

}