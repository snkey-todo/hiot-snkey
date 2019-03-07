package com.snkey.core.injection;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhusheng on 2019/3/6.
 *
 * 自定义注解：@Permission
 * 具体在AuthorizationInterceptor内判断
 *
 * 在Controller的方法参数中使用此注解，该方法在映射时会判断当前用户的操作权限
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
    //用户的角色判断，默认为""，角色参考Role类
    String role() default "";
}
