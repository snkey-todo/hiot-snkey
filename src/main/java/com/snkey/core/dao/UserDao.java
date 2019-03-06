package com.snkey.core.dao;

import com.snkey.core.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Dao层是面向数据库的，Mapper文件接受Dao的操作，去操作数据库
 *
 * @Repository注解：表明这是一个Dao层，供Mapper文件扫描，所有标注@Repository的类都会被扫描
 */
@Repository
public interface UserDao {
    /*保存新用户注册信息*/
    public int saveForRegister(User user);

    /**登录*/
    public User login(User user);

    /*根据用户ID查询用户信息*/
    public User findById(String id);

    /*查询所有用户*/
    public List<User> findAll();

    /*修改用户密码*/
    public int updatePassword(@Param("id") String id, @Param("password") String password);

    /*根据用户id删除用户*/
    public int detele(String id);
}
