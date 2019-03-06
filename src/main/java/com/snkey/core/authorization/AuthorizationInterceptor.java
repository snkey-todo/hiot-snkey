package com.snkey.core.authorization;


import com.snkey.core.authorization.model.TokenModel;
import com.snkey.core.authorization.service.TokenManager;
import com.snkey.core.config.Constant;
import com.snkey.core.dao.UserDao;
import com.snkey.core.entity.Result;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * Created by zhusheng on 2019/3/5.
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    private TokenManager manager;

    @Autowired
    private UserDao userDao;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return false;

    }
}
