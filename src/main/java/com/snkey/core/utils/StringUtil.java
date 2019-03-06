package com.snkey.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static Boolean isEmpty(String str){
        if(null!=str && str.trim().length()>0 && !"null".equals(str)){
            return false;
        }
        return true;
    }

    /**
     * 将unicode码转为字符串
     * @param unicode
     * @return
     */
    public static String Unicode2String(String unicode){
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(unicode);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            unicode = unicode.replace(matcher.group(1), ch+"" );
        }
        return unicode;
    }

}
