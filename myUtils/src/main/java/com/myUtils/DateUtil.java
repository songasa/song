package com.myUtils;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
    final static String formatter = "yyyy-MM-dd HH:mm:ss";

    public DateUtil() {
    }

    public static String getCurDateTime(String dateTimeFormatter) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("".equals(dateTimeFormatter)?formatter:dateTimeFormatter));
    }


    //test
    public static void main(String[] args) {
        System.out.println(TimeZone.getDefault());
        Date date = new Date();
        System.out.println(date);
        String yyyy_mm_dd =
                new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss", Locale.CHINA)
                        .format(date);
        System.out.println(yyyy_mm_dd);

    }
}
