package com.ip.rest.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ssX");

    public static String getTimestamp(){

        Date date = new Date();
        return dateFormat.format(date);
    }
}
