package com.snkey.core.service.impl;

import com.snkey.core.dao.UserDao;
import com.snkey.core.entity.Result;
import com.snkey.core.entity.User;
import com.snkey.core.service.IUserService;
import com.snkey.core.utils.MD5Util;
import com.snkey.core.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service层实现类
 *
 * @Service注解：表明这是一个Service类，处理业务逻辑的
 */
@Service
public class IUserServiceImpl implements IUserService {
    /**
     * @Autowired注解，用于自动导入的，所有用到Dao的地方，都可以使用该方式进行导入，不用去new对象
     */
    @Autowired
    private UserDao userDao;

    /**
     * 用户注册的业务逻辑
     * @param user
     * @return
     */
    @Override
    public int register(User user) {

        return 0;
    }

    /**
     * 用户登录的业务逻辑
     * @param user
     * @return
     */
    @Override
    public Result login(User user) {
        if(StringUtil.isEmpty(user.getUsername()) || StringUtil.isEmpty(user.getPassword())){
            return new Result(0, "username or password can not be empty", null);
        }
        //用户密码进行md5加密
        String md5Password = MD5Util.getMD5Str(user.getPassword());
        user.setPassword(md5Password);
        User result_user = userDao.login(user);
        if(result_user == null){
            return new Result(0, "username or password is error", null);
        }
        return new Result(1, "login success", user);
    }

    @Override
    public boolean isPassword(String password) {
        //正则表达式的模式，正则规划：密码、字母、数字下划线组成6-18位
        Pattern pattern = Pattern.compile( "^[a-zA-Z]\\w{5,17}$");
        //正则表达式的匹配器
        Matcher m = pattern.matcher(password);
        //进行正则匹配
        return m.matches();
    }

}
