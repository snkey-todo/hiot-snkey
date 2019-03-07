package com.snkey.core.utils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhusheng on 2019/3/7.
 */
public class DateUtil {
    private static final Log logger = LogFactory.getLog(DateUtil.class);

    /**
     * 将时间字符串转换为指定格式
     * @param strDate
     * @return
     */
    public static Date parseDateDayFormat(String strDate) {
        if (strDate == null || strDate.trim().length() == 0) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        synchronized (dateFormat) {
            Date date = null;
            try {
                dateFormat.applyPattern("yyyy-MM-dd");
                date = dateFormat.parse(strDate);
            } catch (ParseException pe) {
                logger.error("DateUtil", pe);
            }
            return date;
        }
    }
}
