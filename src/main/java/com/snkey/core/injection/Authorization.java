package com.snkey.core.injection;


/**
 * Created by zhusheng on 2019/3/6.
 *
 * 自定义注解   @Authorization
 *
 * 具体在AuthorizationInterceptor内判断
 *
 * 在Controller的方法上使用此注解，该方法在映射时会检查用户是否登录，并返回错误信息
 *
 * 不加此注解拦截器AuthorizationInterceptor也会自动拦截，只不过没有错误信息，只返回HTTP code:400 Bad Request
 *
 * 使用该注解可以返回自定义的错误信息
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 下面是自定义注解的标准格式
 *
 * 1、@Target(ElementType.METHOD)：作用是让Java的Method反射类知道我们自定义了一个注解，然后我们就可以直接使用该注解了
 * 2、@Retention(RetentionPolicy.RUNTIME)：Retention滞留的意思，表明我的注解是在运行时进行滞留检查的
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {
}
