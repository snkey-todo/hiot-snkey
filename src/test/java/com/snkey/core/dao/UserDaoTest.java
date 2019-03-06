package com.snkey.core.dao;

import com.snkey.core.entity.User;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Date;

/**
 * Junit测试类
 *
 * 必须添加以下2个注解：
 * @RunWith(SpringJUnit4ClassRunner.class)   ：使用Junit框架进行测试
 @ContextConfiguration(locations = {"classpath:applicationContext.xml"})： 指定applicationContext文件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void saveForRegister() throws Exception {
        User user = new User();
        user.setId("125");
        user.setEmail("078945567@qq.com");
        user.setPassword("123123");
        user.setPhone("17600561234");
        user.setUsername("krystal");
        user.setImg("123123");
        user.setIs_active(32);
        user.setIs_developer(12);
        user.setIs_staff(23);
        user.setIs_superuser(11);
        user.setDate_joined(new Date());
        user.setLastlogin(new Date());

        int result_code = userDao.saveForRegister(user);
        System.out.println("resultcode:" + result_code);
    }

    @Test
    public void findById() throws Exception {
        System.out.println( userDao.findById("123").toString());
    }

    @Test
    public void findAll() throws Exception {
        System.out.println(userDao.findAll() == null ? "测试失败" : "测试成功");
    }

    @Test
    public void updatePassword() throws Exception {
        System.out.println(userDao.updatePassword("123", "567567") );
    }

    @Test
    public void detele() throws Exception {
        System.out.println(userDao.detele("123"));
    }

}