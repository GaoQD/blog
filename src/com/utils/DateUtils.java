package com.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateUtils
 * @Author THINK
 * @Date 2019/9/18 9:44
 */

public class DateUtils {

    /**
     * date对象转换成String (datetime)
     * @param date
     * @return
     */
    public static String getFormatDate(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        return format.format(date);
    }

    /**
     * String(datetime)转换成date对象
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getDate(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(date);
    }
}
