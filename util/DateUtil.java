package com.study.shop.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String formatDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String getCurrentToday() {
        Date today = new Date();
        String now = formatDate(today);
        return now.substring(0, 11);
    }
}