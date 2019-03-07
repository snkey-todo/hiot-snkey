package com.snkey.core.dao;


import com.snkey.core.entity.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhusheng on 2019/3/6.
 *
 * Dao层是面向数据库的，Mapper文件接受Dao的操作，去操作数据库
 *  @Repository注解：表明这是一个Dao层，供Mapper文件扫描，所有标注@Repository的类都会被扫描
 *
 */
@Repository
public interface UserDao {
    /**
     *  1、保存新用户注册信息
     *  2、登录
     *  3、根据用户ID查询用户信息
     *  4、查询所有用户
     *  5、修改用户密码
     *  6、根据用户id删除用户
     */
    /**/
    public int saveForRegister(User user);
    public User login(User user);
    public User findById(String id);
    public List<User> findAll();
    public int updatePassword(@Param("id") String id, @Param("password") String password);
    public int detele(String id);
}
