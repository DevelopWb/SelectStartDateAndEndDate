package com.datepicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ${王sir} on 2017/11/22.
 * application
 */

public class PublicUtils {


    /**
     * 通过日期获取对应的星期
     *
     * @param time
     * @return
     */
    public static String GetWeekFromDate(String time) {

        Calendar cal = Calendar.getInstance();

        int i = -1;

// 对 calendar 设置时间的方法

// 设置传入的时间格式

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");

// 指定一个日期

            Date date;

            date = dateFormat.parse(time);

            cal.setTime(date);

            i = cal.get(Calendar.DAY_OF_WEEK);

        } catch (java.text.ParseException e) {


            e.printStackTrace();

        }
        String week = "";
        if (i == 1) {
            week = "星期日";
        }
        if (i == 2) {
            week = "星期一";
        }
        if (i == 3) {
            week = "星期二";
        }
        if (i == 4) {
            week = "星期三";
        }
        if (i == 5) {
            week = "星期四";
        }
        if (i == 6) {
            week = "星期五";
        }
        if (i == 7) {
            week = "星期六";
        }
        return week;
    }

}
