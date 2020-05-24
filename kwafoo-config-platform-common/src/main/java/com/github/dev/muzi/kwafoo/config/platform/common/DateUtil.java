package com.github.dev.muzi.kwafoo.config.platform.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private DateUtil() {
    }

    private static Calendar getDateBeforeToday(int before) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, before);
        return calendar;
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past 天数
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(today);
    }

    /**
     * 获取未来 第 past 天的日期
     *
     * @param past 天数
     */
    public static String getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(today);
    }

    /**
     * 根据给定格式获取当前的日期字符串
     * @param dateFormatStr 解析格式
     */
    public static String getNowDate(String dateFormatStr) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormatStr);
        return format.format(new Date());
    }
}
