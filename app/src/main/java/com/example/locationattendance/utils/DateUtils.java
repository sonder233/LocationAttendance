package com.example.locationattendance.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String getDataString(){
        Date dateTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm E");
        String time_str = sdf.format(dateTime);
        return time_str;
    }
}
