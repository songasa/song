package com.myUtils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    final static String formatter = "yyyy-MM-dd HH:mm:ss";

    public DateUtil() {
    }

    public static String getCurDateTime(String dateTimeFormatter) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("".equals(dateTimeFormatter)?formatter:dateTimeFormatter));
    }
}
