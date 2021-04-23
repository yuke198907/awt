package com.simpo.tracker.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期相关工具类
 */
public class DateTools {

    /**
     * 根据日期及格式获取日期字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String getDateString(Date date, String format) {
        SimpleDateFormat yfm = new SimpleDateFormat(format);
        return yfm.format(date);
    }

    /**
     * 根据日期字符串及格式获取日期
     *
     * @param dateString
     * @param format
     * @return
     */
    public static Date getDate(String dateString, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 编辑日期的年份
     *
     * @param date
     * @param num
     * @return
     */
    public static Date editYear(Date date, int num) {
        Calendar dt = Calendar.getInstance();
        dt.setTime(date);
        dt.add(Calendar.YEAR, num);
        return dt.getTime();
    }

    /**
     * 编辑日期的月份
     *
     * @param date
     * @param num
     * @return
     */
    public static Date editMonth(Date date, int num) {
        Calendar dt = Calendar.getInstance();
        dt.setTime(date);
        dt.add(Calendar.MONTH, num);
        return dt.getTime();
    }

    /**
     * 编辑日期的日
     *
     * @param date
     * @param num
     * @return
     */
    public static Date editDay(Date date, int num) {
        Calendar dt = Calendar.getInstance();
        dt.setTime(date);
        dt.add(Calendar.DAY_OF_MONTH, num);
        return dt.getTime();
    }

    /**
     * 编辑日期的时
     *
     * @param date
     * @param num
     * @return
     */
    public static Date editHour(Date date, int num) {
        Calendar dt = Calendar.getInstance();
        dt.setTime(date);
        dt.add(Calendar.HOUR, num);
        return dt.getTime();
    }

    /**
     * 编辑日期的秒
     *
     * @param date
     * @param num
     * @return
     */
    public static Date editSecond(Date date, int num) {
        Calendar dt = Calendar.getInstance();
        dt.setTime(date);
        dt.add(Calendar.SECOND, num);
        return dt.getTime();
    }
}
