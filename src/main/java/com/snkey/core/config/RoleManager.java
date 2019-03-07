package com.snkey.core.config;


import com.snkey.core.entity.User;

/**
 * Created by zhusheng on 2019/3/7.
 */
public class RoleManager {
    /**
     * 权限规则：
     * 1：超级管理员有开发者和APP使用者权限
     * 2、开发者有APP使用者权限。
     * 3、超级管理员、开发者和APP使用者权限相互独立。
     */
    public static final String ADMIN = "admin";
    public static final String DEVELOPER = "developer";
    public static final String STAFF = "staff";
    public static final String DEVICE = "device";

    public static boolean roleAdmin(User user) {
        int superuser = user.getIs_superuser() == null ? 0 : user.getIs_superuser();
        if (superuser == 1) {
            return true;
        }
        return false;
    }
    public static boolean roleDeveloper(User user) {
        int deve = user.getIs_developer() == null ? 0 : user.getIs_developer();
        if (deve == 1){
            return true;
        }
        return false;
    }
    public static boolean roleStaff(User user) {
        int sta = user.getIs_staff() == null ? 0 : user.getIs_staff();
        if (sta == 1) {
            return true;
        }
        return false;
    }
}
