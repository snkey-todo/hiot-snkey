package com.snkey.core.authorization;


import com.snkey.core.authorization.model.TokenModel;
import com.snkey.core.authorization.service.TokenManager;
import com.snkey.core.config.Constant;
import com.snkey.core.dao.UserDao;
import com.snkey.core.entity.Result;
import com.snkey.core.entity.User;
import com.snkey.core.injection.Authorization;
import com.snkey.core.injection.Permission;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private UserDao userDao;


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 验证1：验证是否是HandlerMethod
         *
         * 验证2：验证token是否有效
         * 自定义的注解都会映射到Method中，可以在请求头中获取我们的token值
         * 如果token为true，我们接下来验证token是用户token还是设备token；如果是用户token，接下来我们验证用户级别
         *
         * 验证3：验证token失败，我们验证是否使用了Authorization注解
         * 如果使用了该注解，我们返回自定义的错误；如果没有使用该注解，我们返回的默认的错误
         *
         */

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String authorization = request.getHeader(Constant.AUTHORIZATION);

        TokenModel model = tokenManager.getToken(authorization);
        Boolean result = tokenManager.checkToken(model);

        if (result) {
           //TODO
        }

        //如果验证token失败，并且方法注明了Authorization，返回401错误
        if (method.getAnnotation(Authorization.class) != null) {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "application/json;charset=UTF-8");//这句话是解决乱码的
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.write(JSONObject.fromObject(result).toString()); //.append(new ObjectMapper().writeValueAsString(result));//
            } catch (IOException ie) {
                ie.printStackTrace();
                Result result1 = new Result(0,"服务器异常",null);
                out.write(JSONObject.fromObject(result1).toString());
            } finally {
                if (out != null) {
                    out.close();
                }
            }
            return false;
        }
        return true;
    }
}
