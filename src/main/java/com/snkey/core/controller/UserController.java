package com.snkey.core.controller;

import com.snkey.core.entity.Result;
import com.snkey.core.entity.User;
import com.snkey.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller层是面向web UI，面向前端的
 *
 * @RestController 注解：表明该类为Controller类，@RestController和@Controller都可以，区别在于一个是Rest风格的
 */
@RestController

public class UserController {

    @Autowired
    private IUserService userService;

    private Result login(User user){

        return userService.login(user);
    }
}
